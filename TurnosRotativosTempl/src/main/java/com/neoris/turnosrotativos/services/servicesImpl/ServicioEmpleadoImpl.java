package com.neoris.turnosrotativos.services.servicesImpl;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repository.EmpleadoRepository;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
@Service
public class ServicioEmpleadoImpl implements ServicioEmpleado {
    @Autowired
    EmpleadoRepository repositorioEmpleado;
    @Override
    public ResponseEntity<Object> encontrarEmpleado(Integer id) {
        Optional<Empleado> empleado=this.repositorioEmpleado.findById(id);
        if (empleado.isPresent()){
            return new ResponseEntity<>(empleado.map(EmpleadoDTO::new),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("No se encontro el empleado con Id: "+id,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<EmpleadoDTO>> listaEmpleados() {
        List<EmpleadoDTO> listaEmpleados=repositorioEmpleado.findAll().stream().map(EmpleadoDTO::new).collect(toList());
        return new ResponseEntity<>(listaEmpleados,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> agregarEmpleado(EmpleadosRecepDTO empleadosRecepDTO) {

        if(Period.between(empleadosRecepDTO.getFechaNacimiento(), LocalDate.now()).getYears()<18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años", HttpStatus.BAD_REQUEST);
        }
        if (encontrarDni(empleadosRecepDTO.getNroDocumento())){
            return new ResponseEntity<>("Ya existe un empleado con el documento ingresado", HttpStatus.CONFLICT);
        }
        if (encontrarEmail(empleadosRecepDTO.getEmail())){
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado",HttpStatus.CONFLICT);
        }
        if(LocalDate.now().isBefore(empleadosRecepDTO.getFechaIngreso())){
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al dia de la fecha",HttpStatus.BAD_REQUEST);
        }
        if (LocalDate.now().isBefore(empleadosRecepDTO.getFechaNacimiento())){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al dia de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if (!empleadosRecepDTO.getNombre().matches("^[a-zA-Z]+$")){
            return new ResponseEntity<>("Solo se permiten letras en el campo nombre",HttpStatus.BAD_REQUEST);
        }
        if (!empleadosRecepDTO.getApellido().matches("^[a-zA-Z]+$")){
            return new ResponseEntity<>("Solo se permiten letras en el campo apellido",HttpStatus.BAD_REQUEST);
        }
        Empleado nuevoEmpleado=new Empleado(empleadosRecepDTO.getNroDocumento(), empleadosRecepDTO.getNombre(), empleadosRecepDTO.getApellido(), empleadosRecepDTO.getEmail(), empleadosRecepDTO.getFechaNacimiento(),empleadosRecepDTO.getFechaIngreso());
        repositorioEmpleado.save(nuevoEmpleado);
        EmpleadoDTO mostrarEmpleado= new EmpleadoDTO(nuevoEmpleado);
        return new ResponseEntity<>(mostrarEmpleado,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> modificarEmpleado(Integer nroDocumento, EmpleadosRecepDTO empleadosRecepDTO) {
        Empleado empleado=repositorioEmpleado.findByNroDocumento(nroDocumento).orElse(null);
        if(empleado==null){
            return new ResponseEntity<>("Empleado no encontrado",HttpStatus.NOT_FOUND);
        }
        if (Period.between(empleadosRecepDTO.getFechaNacimiento(),LocalDate.now()).getYears()<18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años",HttpStatus.BAD_REQUEST);
        }
        if (encontrarDni(empleadosRecepDTO.getNroDocumento())){
            return new ResponseEntity<>("Ya existe un empleado con el documento ingresado", HttpStatus.CONFLICT);
        }
        if (encontrarEmail(empleadosRecepDTO.getEmail())){
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado",HttpStatus.CONFLICT);
        }
        if(LocalDate.now().isBefore(empleadosRecepDTO.getFechaIngreso())){
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al dia de la fecha",HttpStatus.BAD_REQUEST);
        }
        if (LocalDate.now().isBefore(empleadosRecepDTO.getFechaNacimiento())){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al dia de la fecha.",HttpStatus.BAD_REQUEST);
        }
        if (!empleadosRecepDTO.getNombre().matches("^[a-zA-Z]+$")){
            return new ResponseEntity<>("Solo se permiten letras en el campo nombre",HttpStatus.BAD_REQUEST);
        }
        if (!empleadosRecepDTO.getApellido().matches("^[a-zA-Z]+$")){
            return new ResponseEntity<>("Solo se permiten letras en el campo apellido",HttpStatus.BAD_REQUEST);
        }
        empleado.setNroDocumento(empleado.getNroDocumento());
        empleado.setNombre(empleadosRecepDTO.getNombre());
        empleado.setApellido(empleadosRecepDTO.getApellido());
        empleado.setEmail(empleadosRecepDTO.getEmail());
        empleado.setFechaNacimiento(empleadosRecepDTO.getFechaNacimiento());
        empleado.setFechaIngreso(empleadosRecepDTO.getFechaIngreso());
        repositorioEmpleado.save(empleado);
        return new ResponseEntity<>(new EmpleadoDTO(empleado),HttpStatus.OK);
    }

    @Override
    public Boolean encontrarDni(Integer dni) {
        return repositorioEmpleado.existsByNroDocumento(dni);
    }

    @Override
    public Boolean encontrarEmail(String email) {
        return repositorioEmpleado.existsByEmail(email);
    }

    @Override
    public ResponseEntity<Object> eliminarEmpleado(Integer id) {
        if (repositorioEmpleado.existsById(id)){
            return new ResponseEntity<>("No se encontro el empleado con id: "+id,HttpStatus.NOT_FOUND);
        }
        repositorioEmpleado.deleteById(id);
        return new ResponseEntity<>("El empleado fue eliminado con exito",HttpStatus.NO_CONTENT);
    }
}
