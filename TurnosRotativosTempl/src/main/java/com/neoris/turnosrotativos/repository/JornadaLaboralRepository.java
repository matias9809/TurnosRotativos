package com.neoris.turnosrotativos.repository;

import com.neoris.turnosrotativos.entities.JornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaLaboralRepository extends JpaRepository<JornadaLaboral,Integer> {

}
