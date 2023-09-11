package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import org.springframework.http.ResponseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface ServicioEmpleado {
     public ResponseEntity<Object> encontrarEmpleado(Integer id);
    public ResponseEntity<List<EmpleadoDTO>> listaEmpleados();
    public ResponseEntity<Object> agregarEmpleado(EmpleadosRecepDTO empleado);
    public ResponseEntity<Object> modificarEmpleado(Integer nroDocumento,EmpleadosRecepDTO empleadosRecepDTO);
    public Boolean encontrarDni(Integer dni);
    public Boolean encontrarEmail(String email);
    public ResponseEntity<Object> eliminarEmpleado(Integer id);
}
