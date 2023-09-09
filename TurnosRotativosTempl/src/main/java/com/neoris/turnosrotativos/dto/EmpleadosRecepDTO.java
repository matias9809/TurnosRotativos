package com.neoris.turnosrotativos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmpleadosRecepDTO {
    @NotNull(message = "El numero de documento no puede ser nulo")
    @NotBlank(message = "El numero de documento  no puede estar en blanco")
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

    public EmpleadosRecepDTO(Integer nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        Email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.fehcaCreacion=LocalDateTime.now();
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

    public LocalDateTime getFehcaCreacion() {
        return fehcaCreacion;
    }
}
