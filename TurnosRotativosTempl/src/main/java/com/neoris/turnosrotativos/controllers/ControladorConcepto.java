package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dto.ConceptoDTO;
import com.neoris.turnosrotativos.services.ServiceConcepto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorConcepto {
    @Autowired
    ServiceConcepto serviceConcepto;
    @GetMapping("/concepto")
    public ResponseEntity<List<ConceptoDTO>> listaConcepto(){
        return serviceConcepto.obtenerConceptos();
    }
}
