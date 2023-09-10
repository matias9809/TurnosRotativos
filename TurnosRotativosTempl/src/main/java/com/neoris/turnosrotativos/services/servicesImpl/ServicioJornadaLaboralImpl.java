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
        if (concepto.getNombre().equals("Turno Extra")||concepto.getNombre().equals("Turno Normal")){
            if (jornada.getHorasTrabajadas()==null){
                return new ResponseEntity<>("hs Trabajadas es obligatorio para el concepto ingresado.",HttpStatus.BAD_REQUEST);
            }
            else if (jornada.getHorasTrabajadas()>concepto.getHsMinimo()&&jornada.getHorasTrabajadas()>concepto.getHsMaximo()){
                return new ResponseEntity<>("El rango de horas que se puede cargar para este concepto es de "+concepto.getHsMinimo()+"-"+concepto.getHsMaximo()+".",HttpStatus.BAD_REQUEST);
            }
        }
        if (concepto.getNombre().equals("Dia Libre")){
            if (jornada.getHorasTrabajadas()>0){
                return new ResponseEntity<>("El concepto engresado no requiere el ingreso de 'hs Trabajadas'.",HttpStatus.BAD_REQUEST);
            }
        }
        if (!empleado.getListaJornadas().stream().filter(jor->jor.getFecha()==jornada.getFecha()&&concepto.getNombre().equals("Dia Libre")).collect(Collectors.toList()).isEmpty()){
            return new ResponseEntity<>("El empleado cuenta con un dia libre en esa fecha",HttpStatus.BAD_REQUEST);
        }
        if (empleado.getListaJornadas().stream().filter(jor->jor.getFecha()==jornada.getFecha()&&conceptoRepository.findById(jor.getIdConcepto()).orElse(null).getNombre().equals(concepto.getNombre())).collect(Collectors.toList()).size()==1){
            return new ResponseEntity<>("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada. ",HttpStatus.BAD_REQUEST);
        }
        if(!empleado.getListaJornadas().stream().filter(jorn -> jorn.getFecha() == jornada.getFecha() && (jorn.getHorasTrabajadas() + jornada.getHorasTrabajadas()) > 12
        ).collect(Collectors.toList()).isEmpty()){
            return new ResponseEntity<>("El empleado no puede cargar mas de 12 horas trabajadas en un dia",HttpStatus.BAD_REQUEST);
        }
        if (!empleado.getListaJornadas().stream().filter(jor->jor.getFecha()==jornada.getFecha()&&(concepto.getNombre().equals("Turno Extra")||concepto.getNombre().equals("Turno Normal"))).collect(Collectors.toList()).isEmpty()){
            return new ResponseEntity<>("El empleado no puede cargar un Dia Libre se carg un turno previamente para la fecha ingresada.",HttpStatus.BAD_REQUEST);
        }
        LocalDate primerDia=obtenerPrimerDia(jornada.getFecha());
        List<JornadaLaboral> jornadasFiltradas=new ArrayList<>();
        for (int i=0; i<6;i++){
            primerDia.plusDays(i);
            for (JornadaLaboral jor:
                 empleado.getListaJornadas()) {
                if (jor.getFecha()==primerDia){
                    jornadasFiltradas.add(jor);
                }
            }
        }
        int horasTrabajadasEnLaSemana=0;
        if(jornadasFiltradas.contains(true)){
            for (int i=0; i<=jornadasFiltradas.size();i++){
                horasTrabajadasEnLaSemana+=jornadasFiltradas.get(i).getHorasTrabajadas();
            }
        }
        if (horasTrabajadasEnLaSemana+ jornada.getHorasTrabajadas()>48){
            return new ResponseEntity<>("El empleado ingresado supera las 48 horas semanales.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto()==conceptoRepository.findByNombre("Turno Extra").get().getId()).collect(Collectors.toList()).size()==3){
            return new ResponseEntity<>("El empleado ya cuenta con 3 turnos extra esta semana.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto()==conceptoRepository.findByNombre("Turno Normal").get().getId()).collect(Collectors.toList()).size()==5){
            return new ResponseEntity<>("El empleado ya cuenta con 5 turnos normales esta semana.",HttpStatus.BAD_REQUEST);
        }
        if (jornadasFiltradas.stream().filter(jor->jor.getIdConcepto()==conceptoRepository.findByNombre("Dia Libre").get().getId()).collect(Collectors.toList()).size()==1){
            return new ResponseEntity<>("El empleado no cuenta con mas dias libres esta semana.",HttpStatus.BAD_REQUEST);
        }
        JornadaLaboral nuevaJornada=new JornadaLaboral(jornada.getFecha(),jornada.getHorasTrabajadas());
        empleado.agregarJornada(nuevaJornada);
        concepto.agregarJornadaLaboral(nuevaJornada);
        jornadaLaboralRepository.save(nuevaJornada);
        JornadaLaboralDTO nuevaJornadaDTO=new JornadaLaboralDTO(nuevaJornada.getId(), empleado.getNroDocumento(), empleado.getNombre()+" "+empleado.getApellido(),nuevaJornada.getFecha(),concepto.getNombre(), nuevaJornada.getHorasTrabajadas());
        return new ResponseEntity<>(nuevaJornadaDTO,HttpStatus.OK);
    }
}
