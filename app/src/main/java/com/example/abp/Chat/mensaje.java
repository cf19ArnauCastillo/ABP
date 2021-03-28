package com.example.abp.Chat;

public class mensaje {

    public String escribir1;

    public mensaje(){

    }

    public mensaje(String escribir1){
        this.escribir1 = escribir1;
    }

    public String getmensaje(){
        return escribir1;
    }

    public void setmensaje(String escribir1){
        this.escribir1 = escribir1;
    }
}
