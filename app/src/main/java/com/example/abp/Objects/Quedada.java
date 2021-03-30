package com.example.abp.Objects;

import java.util.ArrayList;

/*
        Hecho por: Cristian Montañés Escobar
        Correo: cf19cristian.montanes@iesjoandaustria.org
 */
public class Quedada {
    protected String id;
    protected String horario;
    protected double latitud;
    protected double longitud;
    protected String aficion;
    protected String ids;


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

    public void setIds(String ids){
        this.ids = ids;
    }
    public String getIds(){return ids;}
}
