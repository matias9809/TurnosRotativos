package com.neoris.turnosrotativos.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmpleadosRecepDTO {
    @NotNull(message = "nro_Documento es obligatorio")
    private Integer nroDocumento;
    @NotNull(message = "nombre es obligatorio")
    @NotBlank(message = "nombre es obligatorio")
    private String nombre;
    @NotNull(message = "apellido es obligatorio")
    @NotBlank(message = "apellido es obligarorio")
    private String apellido;

    @NotNull(message = "email es obligatorio")
    @NotBlank(message = "email es obligatorio")
    @Email(message = "El email ingresado no es correcto.")
    private String email;
    @NotNull(message = "fecha_Nacimiento es obligatorio")
    private LocalDate fechaNacimiento;
    @NotNull(message = "fecha_ingreso es obligatorio")
    private LocalDate fechaIngreso;

    public EmpleadosRecepDTO(Integer nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
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
        return email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }


}
