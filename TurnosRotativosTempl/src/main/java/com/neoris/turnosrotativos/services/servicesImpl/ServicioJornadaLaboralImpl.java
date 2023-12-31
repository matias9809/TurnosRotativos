package com.neoris.turnosrotativos.services.servicesImpl;

import com.neoris.turnosrotativos.dto.JornadaLaboralDTO;
import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.JornadaLaboral;
import com.neoris.turnosrotativos.repository.ConceptoRepository;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.repository.JornadaLaboralRepository;
import com.neoris.turnosrotativos.services.ServicioJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.neoris.turnosrotativos.utilities.Utilidades.obtenerPrimerDia;

@Service
public class ServicioJornadaLaboralImpl implements ServicioJornadaLaboral {
    @Autowired
    private JornadaLaboralRepository jornadaLaboralRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ConceptoRepository conceptoRepository;
    @Override
    public ResponseEntity<Object> crearJornadaLaboral(JornadaLaboralRecepDTO jornada) {
        Empleado empleado=empleadoRepository.findById(jornada.getIdEmpleado()).orElse(null);
        if (empleado==null){
            return new ResponseEntity<>("No existe el empleado ingresado.", HttpStatus.NOT_FOUND);
        }
        Concepto concepto=conceptoRepository.findById(jornada.getIdConcepto()).orElse(null);
        if (concepto==null){
            return new ResponseEntity<>("No existe el concepto ingresado.",HttpStatus.NOT_FOUND);
        }
        List<JornadaLaboral> jornadaLaborals=empleado.getListaJornadas();
        List<JornadaLaboral> listaJOr=jornadaLaborals.stream().filter(jor-> jor.getFecha().isEqual(jornada.getFecha())&&jor.getIdConcepto()==jornada.getIdConcepto()).collect(Collectors.toList());
        if (concepto.getNombre().equals("Turno Extra")||concepto.getNombre().equals("Turno Normal")){
            if (jornada.getHorasTrabajadas()==null){
                return new ResponseEntity<>("hs Trabajadas es obligatorio para el concepto ingresado.",HttpStatus.BAD_REQUEST);
            }
            else if (jornada.getHorasTrabajadas()<concepto.getHsMinimo()||jornada.getHorasTrabajadas()>concepto.getHsMaximo()){
                return new ResponseEntity<>("El rango de horas que se puede cargar para este concepto es de "+concepto.getHsMinimo()+"-"+concepto.getHsMaximo()+".",HttpStatus.BAD_REQUEST);
            }
        }

        if (concepto.getNombre().equals("Dia Libre")){
            List<JornadaLaboral> listJOrr=jornadaLaborals.stream()
                    .filter(jor->jor.getFecha().isEqual(jornada.getFecha())&&!jor.getIdConcepto().equals(3))
                    .collect(Collectors.toList());
            if (jornada.getHorasTrabajadas()>0){
                return new ResponseEntity<>("El concepto engresado no requiere el ingreso de 'hs Trabajadas'.",HttpStatus.BAD_REQUEST);
            }
            else if (!listJOrr.isEmpty()){
                return new ResponseEntity<>("El empleado no puede cargar un Dia Libre si cargo un turno previamente para la fecha ingresada.",HttpStatus.BAD_REQUEST);
            }

        }
        if (!empleado.getListaJornadas().stream().filter(jor->jor.getFecha().isEqual(jornada.getFecha())&&jor.getIdConcepto().equals(3)).collect(Collectors.toList()).isEmpty()){
            return new ResponseEntity<>("El empleado cuenta con un dia libre en esa fecha",HttpStatus.BAD_REQUEST);
        }
        if (!listaJOr.isEmpty()){
            return new ResponseEntity<>("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada. ",HttpStatus.BAD_REQUEST);
        }

        if(!empleado.getListaJornadas().stream().filter(jorn -> jorn.getFecha().isEqual(jornada.getFecha())  && (jorn.getHorasTrabajadas() + jornada.getHorasTrabajadas()) > 12
        ).collect(Collectors.toList()).isEmpty()){
            return new ResponseEntity<>("El empleado no puede cargar mas de 12 horas trabajadas en un dia",HttpStatus.BAD_REQUEST);
        }
        LocalDate primerDia=obtenerPrimerDia(jornada.getFecha());
        List<JornadaLaboral> jornadasFiltradas=new ArrayList<>();
        for (int i=0; i<6;i++){
            LocalDate fecha=primerDia.plusDays(i);
            for (JornadaLaboral jor:
                 empleado.getListaJornadas()) {
                if (jor.getFecha().isEqual(fecha)){
                    jornadasFiltradas.add(jor);
                }
            }
        }
        int horasTrabajadasEnLaSemana=0;
        if(!jornadasFiltradas.isEmpty()){
            for (JornadaLaboral jor:jornadasFiltradas){
                horasTrabajadasEnLaSemana=horasTrabajadasEnLaSemana+jor.getHorasTrabajadas();
            }
        }
        if (horasTrabajadasEnLaSemana+ jornada.getHorasTrabajadas()>48){
            return new ResponseEntity<>("El empleado ingresado supera las 48 horas semanales.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto().equals(2)).collect(Collectors.toList()).size()==3&&concepto.getId().equals(2)){
            return new ResponseEntity<>("El empleado ya cuenta con 3 turnos extra esta semana.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto().equals(1)).collect(Collectors.toList()).size()==5&&concepto.getId().equals(1)){
            return new ResponseEntity<>("El empleado ya cuenta con 5 turnos normales esta semana.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto().equals(3)).collect(Collectors.toList()).size()==1&&concepto.getId().equals(3)){
            return new ResponseEntity<>("El empleado no cuenta con mas dias libres esta semana.",HttpStatus.BAD_REQUEST);
        }
        JornadaLaboral nuevaJornada=new JornadaLaboral(jornada.getFecha(),jornada.getHorasTrabajadas());
        empleado.agregarJornada(nuevaJornada);
        concepto.agregarJornadaLaboral(nuevaJornada);
        jornadaLaboralRepository.save(nuevaJornada);
        empleadoRepository.save(empleado);
        conceptoRepository.save(concepto);
        JornadaLaboralDTO nuevaJornadaDTO=new JornadaLaboralDTO(nuevaJornada.getId(), empleado.getNroDocumento(), empleado.getNombre()+" "+empleado.getApellido(),nuevaJornada.getFecha(),concepto.getNombre(), nuevaJornada.getHorasTrabajadas());
        return new ResponseEntity<>(nuevaJornadaDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> jornadaEmpleado(@RequestParam(required = false)Integer nroDocumento,@RequestParam(required = false) LocalDate fecha) {
        if (nroDocumento!=null&&fecha==null){
            Empleado empleado=empleadoRepository.findByNroDocumento(nroDocumento).orElse(null);
            List<JornadaLaboral> lista=empleado.getListaJornadas();
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
        else if(nroDocumento==null&&fecha!=null){
            List<JornadaLaboralDTO> lista=jornadaLaboralRepository.findAll()
                    .stream()
                    .filter(jorFil->jorFil.getFecha().isEqual(fecha))
                    .map(jor->{
                        Empleado empleado=empleadoRepository.findById(jor.getIdEmpleado()).orElse(null);
                        Concepto concepto=conceptoRepository.findById(jor.getIdConcepto()).orElse(null);
                        return new JornadaLaboralDTO(jor.getId(), empleado.getNroDocumento(), empleado.getNombre()+" "+empleado.getApellido(),jor.getFecha(),concepto.getNombre(), jor.getHorasTrabajadas());

                    }).collect(Collectors.toList());
            return new ResponseEntity<>(lista,HttpStatus.OK);
        } else if (nroDocumento!=null&&fecha!=null) {
            Empleado empleado=empleadoRepository.findByNroDocumento(nroDocumento).orElse(null);
            if (empleado==null){
                return new ResponseEntity<>("No existe un empleado con el documento ingresado",HttpStatus.BAD_REQUEST);
            }
            List<JornadaLaboralDTO> lista=empleado.getListaJornadas()
                    .stream()
                    .filter(jorFil->jorFil.getFecha().isEqual(fecha))
                    .map(jor->{
                        Concepto concepto=conceptoRepository.findById(jor.getIdConcepto()).orElse(null);
                        return new JornadaLaboralDTO(jor.getId(), empleado.getNroDocumento(), empleado.getNombre()+" "+empleado.getApellido(),jor.getFecha(), concepto.getNombre(), jor.getHorasTrabajadas());
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
        else {
            List<JornadaLaboralDTO> lista=jornadaLaboralRepository.findAll()
                    .stream()
                    .map(jorl -> {
                        Concepto concepto= conceptoRepository.findById(jorl.getIdConcepto()).orElse(null);
                        Empleado empleado=empleadoRepository.findById(jorl.getIdEmpleado()).orElse(null);
                        return new JornadaLaboralDTO(jorl.getId(), empleado.getNroDocumento(), empleado.getNombre()+" "+empleado.getApellido(),jorl.getFecha(),concepto.getNombre(), jorl.getHorasTrabajadas());
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }
}
