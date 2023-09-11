package com.neoris.turnosrotativos.repository;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Integer> {
    Optional<Empleado> findByNroDocumento(Integer nroDocumento);
    Boolean existsByEmail(String email);
    Boolean existsByNroDocumento(Integer Documento);

}
