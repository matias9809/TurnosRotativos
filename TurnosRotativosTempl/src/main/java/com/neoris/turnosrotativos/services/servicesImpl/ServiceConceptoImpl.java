package com.neoris.turnosrotativos.services.servicesImpl;

import com.neoris.turnosrotativos.dto.ConceptoDTO;
import com.neoris.turnosrotativos.repository.ConceptoRepository;
import com.neoris.turnosrotativos.services.ServiceConcepto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
public class ServiceConceptoImpl implements ServiceConcepto {
    @Autowired
    ConceptoRepository conceptoRepository;
    @Override
    public ResponseEntity<List<ConceptoDTO>> obtenerConceptos() {
        List<ConceptoDTO> listaConceptos=conceptoRepository.findAll().stream().map(ConceptoDTO::new).collect(toList());
        return new ResponseEntity<>(listaConceptos, HttpStatus.OK);
    }
}
