package com.example.abp.Objects;

import java.io.Serializable;

public class User implements Serializable {
    protected String nombre;
    protected String apellidos;
    protected String correo;
    protected String contraseña;
    protected String mote;
    protected String pais;
    protected String ciudad;
    protected int edad;

    public User(String nombre, String apellidos, String correo, String contraseña, String mote, String pais, String ciudad, int edad){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contraseña = contraseña;
        this.mote = mote;
        this.pais = pais;
        this.ciudad = ciudad;
        this.edad = edad;
    }
    public String getNombre() {return nombre;}
    public String getApellidos(){return apellidos;}
    public String getCorreo(){return correo;}
    public String getContraseña(){return contraseña;}
    public String getMote(){return mote;}
    public String getPais(){return pais;}
    public String getCiudad(){return ciudad;}
    public int getEdad(){return edad;}
}
