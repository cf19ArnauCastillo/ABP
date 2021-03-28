package com.example.abp.Chat;

public class Mensaje {
    public String escribir1;

    public Mensaje(){
    }
    public Mensaje(String escribir1){
        this.escribir1 = escribir1;
    }

    public String getmensaje(){
        return escribir1;
    }

    public void setmensaje(String escribir1){
        this.escribir1 = escribir1;
    }
}

