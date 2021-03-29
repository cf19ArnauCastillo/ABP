package com.example.abp.Objects;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    protected String id;
    protected String correo;
    protected String mote;
    protected String photo;

    public User(String id, String correo, String mote, String photo){
        this.id = id;
        this.correo = correo;
        this.mote = mote;
        this.photo = photo;
    }
    public String getMote() {
        return mote;
    }
    public String getCorreo() {
        return correo;
    }
    public String getId(){return id;}
    public String getPhoto(){return photo;}

}
