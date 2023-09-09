package com.neoris.turnosrotativos.exceptions;

public class BussinessException extends RuntimeException{
    public BussinessException(String mensaje){
        super(mensaje);
    }
}
