package com.neoris.turnosrotativos.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
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

    public Empleado(Integer nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        Email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.fehcaCreacion=LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDateTime getFehcaCreacion() {
        return fehcaCreacion;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nroDocumento=" + nroDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", Email='" + Email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento+'\'' +
                ", fechaIngreso=" + fechaIngreso +'\''+
                ", fehcaCreacion=" + fehcaCreacion.toLocalDate()+
                '}';
    }
}