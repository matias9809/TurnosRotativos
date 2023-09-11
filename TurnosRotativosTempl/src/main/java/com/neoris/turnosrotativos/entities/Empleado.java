package com.neoris.turnosrotativos.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;
    @NotNull(message = "nroDocumento es un campo obligatorio")
    private Integer nroDocumento;
    @NotNull(message = "nombre es un campo obligatorio")
    @NotBlank(message = "nombre es un campo obligatorio")
    private String nombre;
    @NotNull(message = "apellido es un campo obligatorio")
    @NotBlank(message = "apellido es un campo obligatorio")
    private String apellido;

    @NotNull(message = "email es un campo obligatorio")
    @NotBlank(message = "email es un campo obligatorio")
    @Email(message = "El email ingresado no es correcto.")
    private String email;
    @NotNull(message = "fechaNacimiento es un campo obligatorio")
    private LocalDate fechaNacimiento;
    @NotNull(message = "fechaIngreso es un campo obligatorio")
    private LocalDate fechaIngreso;
    private LocalDateTime fehcaCreacion;
    @OneToMany(mappedBy = "empleado",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<JornadaLaboral> listaJornadas=new ArrayList<>();
    public Empleado(){}

    public Empleado(Integer nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.fehcaCreacion=LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getFehcaCreacion() {
        return fehcaCreacion;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void agregarJornada(JornadaLaboral jornadaLaboral){
        jornadaLaboral.setEmpleado(this);
        this.listaJornadas.add(jornadaLaboral);
    }

    public List<JornadaLaboral> getListaJornadas() {
        return listaJornadas;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nroDocumento=" + nroDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", Email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento+'\'' +
                ", fechaIngreso=" + fechaIngreso +'\''+
                ", fehcaCreacion=" + fehcaCreacion.toLocalDate()+
                ",listaJornadasLaborales"+this.listaJornadas+
                '}';
    }
}
