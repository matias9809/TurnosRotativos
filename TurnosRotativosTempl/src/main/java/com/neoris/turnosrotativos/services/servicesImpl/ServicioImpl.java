package com.neoris.turnosrotativos.services.servicesImpl;

import com.neoris.turnosrotativos.dto.EmpleadoDTO;
import com.neoris.turnosrotativos.dto.EmpleadosRecepDTO;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repository.RepositorioEmpleado;
import com.neoris.turnosrotativos.services.ServicioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ServicioImpl implements ServicioEmpleado {
    @Autowired
    private RepositorioEmpleado repositorioEmpleado;
    @Override
    public Optional<EmpleadoDTO> encontrarEmpleado(Integer id) {
        Optional<Empleado> empleado=this.repositorioEmpleado.findById(id);
        if (empleado.isPresent()){
            return empleado.map(EmpleadoDTO::new);
        }
        else{
            return null;
        }
    }

    @Override
    public List<EmpleadoDTO> listaEmpleados() {
        return repositorioEmpleado.findAll().stream().map(EmpleadoDTO::new).collect(toList());
    }

    @Override
    public EmpleadoDTO agregarEmpleado(EmpleadosRecepDTO empleado) {

        return null;
    }

    @Override
    public Boolean encontrarDni(Integer dni) {
        return repositorioEmpleado.existsByNroDocumento(dni);
    }

    @Override
    public Boolean encontrarEmail(String email) {
        return repositorioEmpleado.existsByEmail(email);
    }
}
