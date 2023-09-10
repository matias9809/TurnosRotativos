package com.neoris.turnosrotativos.utilities;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Utilidades {
    public static LocalDate obtenerPrimerDia(LocalDate fecha){
        DayOfWeek dia=fecha.getDayOfWeek();

        int diferenciaDias=dia.getValue()-DayOfWeek.MONDAY.getValue();

        return fecha.minusDays(diferenciaDias);
    }
}
