package com.example.abp.Objects;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {

    protected String correo;
    protected String mote;
    protected Uri foto;

    public User(String correo, String mote, Uri foto){
        this.correo = correo;
        this.mote = mote;
        this.foto = foto;
    }
    public Uri getFoto() {
        return foto;
    }


    public String getMote() {
        return mote;
    }


    public String getCorreo() {
        return correo;
    }

}
