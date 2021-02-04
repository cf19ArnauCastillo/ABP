package com.example.abp.Objects;

import java.util.Date;

public class Quedada {
    protected int id;
    protected Date horario;
    protected double latitud;
    protected double longitud;

    public Quedada(int id, Date horario, double latitud, double longitud){
        this.id = id;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public Date getHorario() {
        return horario;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
