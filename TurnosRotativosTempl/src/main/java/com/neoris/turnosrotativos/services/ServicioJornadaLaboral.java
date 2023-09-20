package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface ServicioJornadaLaboral {
    public ResponseEntity<Object> crearJornadaLaboral(JornadaLaboralRecepDTO jornada);
    public ResponseEntity<Object> jornadaEmpleado(Integer nroDocumento,LocalDate fecha);
}
