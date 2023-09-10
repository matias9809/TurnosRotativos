package com.neoris.turnosrotativos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class JornadaLaboralRecepDTO {
    @NotNull(message = "idEmpleado es obligatorio")
    private Integer idEmpleado;
    @NotNull(message = "idConcepto es obligatorio")
    private Integer idConcepto;
    @NotNull(message = "fechca es obligatorio")
    private LocalDate fecha;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer horasTrabajadas;
    public JornadaLaboralRecepDTO(Integer idEmpleado,Integer idConcepto,LocalDate fecha, Integer horasTrabajadas){
        this.idConcepto=idConcepto;
        this.idEmpleado=idEmpleado;
        this.fecha=fecha;
        this.horasTrabajadas=horasTrabajadas;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }
}
