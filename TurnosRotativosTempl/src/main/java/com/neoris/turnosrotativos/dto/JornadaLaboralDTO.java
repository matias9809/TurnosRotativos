package com.neoris.turnosrotativos.dto;

import java.time.LocalDate;

public class JornadaLaboralDTO {
    private Integer id;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    private Integer hsTrabajadas;

    public JornadaLaboralDTO(Integer id, Integer nroDocumento, String nombreCompleto, LocalDate fecha, String concepto, Integer hsTrabajadas) {
        this.id = id;
        this.nroDocumento = nroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.concepto = concepto;
        this.hsTrabajadas = hsTrabajadas;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    @Override
    public String toString() {
        return "JornadaLaboralDTO{" +
                "id=" + id +
                ", nroDocumento=" + nroDocumento +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fecha=" + fecha +
                ", concepto='" + concepto + '\'' +
                ", hsTrabajadas=" + hsTrabajadas +
                '}';
    }
}
