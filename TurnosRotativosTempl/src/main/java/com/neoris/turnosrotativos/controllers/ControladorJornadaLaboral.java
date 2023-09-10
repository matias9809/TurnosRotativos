package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.services.ServiceConcepto;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import com.neoris.turnosrotativos.services.ServicioJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControladorJornadaLaboral {
    @Autowired
    private ServicioJornadaLaboral servicioJornadaLaboral;

    @PostMapping("/jornada")
    public ResponseEntity<Object> crearJornadaLaboral(@Valid @RequestBody JornadaLaboralRecepDTO jornadaLaboralRecepDTO){

        return servicioJornadaLaboral.crearJornadaLaboral(jornadaLaboralRecepDTO);
    }
}
