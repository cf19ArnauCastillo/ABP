package com.example.abp.Objects;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    protected String id;
    protected String correo;
    protected String mote;

    public User(String id, String correo, String mote){
        this.correo = correo;
        this.mote = mote;
    }
    public String getMote() {
        return mote;
    }
    public String getCorreo() {
        return correo;
    }
    public String getId(){return id;}

}
