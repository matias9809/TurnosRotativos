package com.neoris.turnosrotativos.dto;

import com.neoris.turnosrotativos.entities.Empleado;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmpleadoDTO {
    private Integer id;
    @NotNull(message = "El numero de documento no puede ser nulo")
    private Integer nroDocumento;
    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellido;

    @NotNull(message = "El email no puede ser nulo")
    @NotBlank(message = "El email no puede estar en blanco")
    @Email(message = "El email ingresado no es correcto.")
    private String Email;
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @NotBlank(message = "La fecha de nacimiento  no puede estar en blanco")
    private LocalDate fechaNacimiento;
    @NotNull(message = "La fecha de ingreso no puede ser nula")
    @NotBlank(message = "La fecha de ingreso no puede estar en blanco")
    private LocalDate fechaIngreso;
    private LocalDateTime fehcaCreacion;
    public EmpleadoDTO(Empleado empleado){
        this.id= empleado.getId();
        this.nroDocumento= empleado.getNroDocumento();
        this.nombre=empleado.getNombre();
        this.apellido= empleado.getApellido();
        this.Email= empleado.getEmail();
        this.fechaIngreso=empleado.getFechaIngreso();
        this.fechaNacimiento=empleado.getFechaNacimiento();
        this.fehcaCreacion=empleado.getFehcaCreacion();
    }

    public Integer getId() {
        return id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return Email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFehcaCreacion() {
        return fehcaCreacion.toLocalDate();
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "id=" + id +
                ", nroDocumento=" + nroDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", Email='" + Email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                ", fehcaCreacion=" + fehcaCreacion.toLocalDate() +
                '}';
    }
}
