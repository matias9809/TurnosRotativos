package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.JornadaLaboralRecepDTO;
import org.springframework.http.ResponseEntity;

public interface ServicioJornadaLaboral {
    public ResponseEntity<Object> crearJornadaLaboral(JornadaLaboralRecepDTO jornada);
}
