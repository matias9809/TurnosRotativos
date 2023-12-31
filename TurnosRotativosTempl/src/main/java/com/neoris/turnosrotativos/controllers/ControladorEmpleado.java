package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class ControladorEmpleado {
    @Autowired
    ServicioEmpleado servicioEmpleado;
    @PostMapping("/empleado")
    public ResponseEntity<Object> agregarEmpleado(@Valid @RequestBody EmpleadosRecepDTO empleadosRecepDTO){

        return servicioEmpleado.agregarEmpleado(empleadosRecepDTO);
    }
    @GetMapping("/empleado")
    public ResponseEntity<List<EmpleadoDTO>> mostrarEmpleados(){
        return servicioEmpleado.listaEmpleados();
    }

    @GetMapping("/empleado/{id}")
    public ResponseEntity<Object> obternerInformacionEmpleado(@PathVariable String id){
        return servicioEmpleado.encontrarEmpleado(Integer.parseInt(id));
    }
    @PutMapping("/empleado/{empleadoId}")
    public ResponseEntity<Object> modificarEmpleado(@PathVariable String empleadoId,@Valid @RequestBody EmpleadosRecepDTO empleadosRecepDTO){
        return servicioEmpleado.modificarEmpleado(Integer.parseInt(empleadoId),empleadosRecepDTO);
    }
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<Object> eliminarEmpleado(@PathVariable String id){
        return servicioEmpleado.eliminarEmpleado(Integer.parseInt(id));
    }

}
