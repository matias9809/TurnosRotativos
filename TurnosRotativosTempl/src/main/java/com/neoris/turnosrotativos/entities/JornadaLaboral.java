package com.neoris.turnosrotativos.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class JornadaLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;
    @NotNull(message = "")
    private LocalDate fecha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer horasTrabajadas;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="concepto_id")
    private Concepto concepto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empleado_id")
    private Empleado empleado;

    public JornadaLaboral(){
    }

    public JornadaLaboral(LocalDate fecha, Integer horasTrabajadas) {
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpleado() {
        return this.empleado.getId();
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getIdConcepto() {
        return concepto.getId();
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String toString() {
        return "JornadaLaboral{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", horasTrabajadas=" + horasTrabajadas +
                ", IdConcepto=" + concepto.getId() +
                ", IdEmpleado=" + empleado.getId() +
                '}';
    }
}
