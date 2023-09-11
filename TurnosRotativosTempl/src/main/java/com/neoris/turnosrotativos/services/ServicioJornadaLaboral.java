package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ServicioJornadaLaboral {
    public ResponseEntity<Object> crearJornadaLaboral(JornadaLaboralRecepDTO jornada);
    public ResponseEntity<Object> jornadaEmpleado(Integer nroDocumento);
    public  ResponseEntity<Object> listaJornadas();
    public  ResponseEntity<Object> jornadaFecha(LocalDate fecha);
    public ResponseEntity<Object> jornadaEmpleadoFecha(Integer nroDocumento, LocalDate fecha);
}
