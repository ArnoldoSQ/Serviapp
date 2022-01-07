package com.example.proyecto;


import android.graphics.drawable.Drawable;

public class taller {

    String text;
    int id;
    Drawable imagen;
    public String getText() {
        return text;
    }

    public Drawable getImagen() {
        return imagen;
    }


    public taller(String text, Drawable imagen, int id) {
        this.text = text;
        this.imagen = imagen;
        this.id=id;
    }
}
