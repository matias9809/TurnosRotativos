package com.neoris.turnosrotativos.dto;

import com.neoris.turnosrotativos.entities.Concepto;

public class ConceptoDTO {
    private Integer id;
    private String nombre;
    private Boolean laborable;
    private Integer hsMinimo;
    private Integer hsMaximo;
    public ConceptoDTO(Concepto concepto){
        this.id= concepto.getId();
        this.nombre= concepto.getNombre();
        this.hsMinimo=concepto.getHsMinimo();
        this.hsMaximo=concepto.getHsMaximo();
        this.laborable=concepto.getLaborable();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public Integer getHsMinimo() {
        return hsMinimo;
    }

    public Integer getHsMaximo() {
        return hsMaximo;
    }

    @Override
    public String toString() {
        return "ConceptoDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", laborable=" + laborable +
                ", hsMinimo=" + hsMinimo +
                ", hsMaximo=" + hsMaximo +
                '}';
    }
}
