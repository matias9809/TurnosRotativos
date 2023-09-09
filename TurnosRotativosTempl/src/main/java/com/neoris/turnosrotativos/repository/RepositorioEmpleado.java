package com.neoris.turnosrotativos.repository;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioEmpleado extends JpaRepository<Empleado,Long> {
    Optional<Empleado> findById(Integer id);
    Boolean existsByEmail(String email);
    Boolean existsByNroDocumento(Integer Documento);

}
