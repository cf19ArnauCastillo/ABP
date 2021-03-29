package com.example.abp.Objects;

import java.util.Date;

public class Quedada {
    protected String id;
    protected String horario;
    protected double latitud;
    protected double longitud;
    protected String aficion;

    public Quedada(){

    }

    public Quedada(String id, String horario, double latitud, double longitud, String aficion){
        this.id = id;
        this.horario = horario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.aficion = aficion;
    }
    public String getId() {
        return id;
    }

    public String getHorario() {
        return horario;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String getAficion(){ return aficion; }
}
