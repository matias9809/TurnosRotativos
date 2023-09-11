package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.services.ServiceConcepto;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import com.neoris.turnosrotativos.services.ServicioJornadaLaboral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class ControladorJornadaLaboral {
    @Autowired
    private ServicioJornadaLaboral servicioJornadaLaboral;

    @PostMapping("/jornada")
    public ResponseEntity<Object> crearJornadaLaboral(@Valid @RequestBody JornadaLaboralRecepDTO jornadaLaboralRecepDTO){

        return servicioJornadaLaboral.crearJornadaLaboral(jornadaLaboralRecepDTO);
    }
    @GetMapping("/jornada")
    public ResponseEntity<Object> listaJornadas(@RequestParam(required = false )Integer nroDocumento,@RequestParam(required = false) String fecha){
        if (nroDocumento!=null&&fecha==null){
            return servicioJornadaLaboral.jornadaEmpleado(nroDocumento);
        }
        else if(nroDocumento==null&&fecha!=null){
            return servicioJornadaLaboral.jornadaFecha(LocalDate.parse(fecha));
        } else if (nroDocumento!=null&&fecha!=null) {
            return servicioJornadaLaboral.jornadaEmpleadoFecha(nroDocumento,LocalDate.parse(fecha));
        }
        else {
            return servicioJornadaLaboral.listaJornadas();
        }

    }

}
