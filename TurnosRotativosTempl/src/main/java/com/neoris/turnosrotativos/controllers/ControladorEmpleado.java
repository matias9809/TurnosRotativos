package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import com.neoris.turnosrotativos.services.servicesImpl.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@RestController
@RequestMapping("/api")
public class ControladorEmpleado {
    @Autowired
    ServicioEmpleado servicioEmpleado;
    @PostMapping("/empleado")
    public ResponseEntity<Object> agregarEmpleado(@Valid @RequestBody EmpleadosRecepDTO empleadosRecepDTO){
        if(Period.between(empleadosRecepDTO.getFechaNacimiento(), LocalDate.now()).getYears()>=18){
            return new ResponseEntity<>("La edad del empleado no puede ser menor a 18 años",HttpStatus.BAD_REQUEST);
        }
        if (!servicioEmpleado.encontrarDni(empleadosRecepDTO.getNroDocumento())){
            return new ResponseEntity<>("Ya existe un empleado con el documento ingresado", HttpStatus.CONFLICT);
        }
        if (!servicioEmpleado.encontrarEmail(empleadosRecepDTO.getEmail())){
            return new ResponseEntity<>("Ya existe un empleado con el email ingresado",HttpStatus.CONFLICT);
        }
        if(LocalDate.now().isBefore(empleadosRecepDTO.getFechaIngreso())){
            return new ResponseEntity<>("La fecha de ingreso no puede ser posterior al dia de la fecha",HttpStatus.BAD_REQUEST);
        }
        if (LocalDate.now().isBefore(empleadosRecepDTO.getFechaNacimiento())){
            return new ResponseEntity<>("La fecha de nacimiento no puede ser posterior al dia de la fecha.",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.accepted().body(empleadosRecepDTO);
    }
}
