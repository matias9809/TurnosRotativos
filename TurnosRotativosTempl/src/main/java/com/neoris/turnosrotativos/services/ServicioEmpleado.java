package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import com.neoris.turnosrotativos.entities.Empleado;

import java.util.List;
import java.util.Optional;

public interface ServicioEmpleado {
     public Optional<EmpleadoDTO> encontrarEmpleado(Integer id);
    public List<EmpleadoDTO> listaEmpleados();
    public EmpleadoDTO agregarEmpleado(EmpleadosRecepDTO empleado);
    public Boolean encontrarDni(Integer dni);
    public Boolean encontrarEmail(String email);
}
