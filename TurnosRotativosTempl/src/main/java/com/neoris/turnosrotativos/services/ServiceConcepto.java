package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.ConceptoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceConcepto {
    public ResponseEntity<List<ConceptoDTO>> obtenerConceptos();
}
