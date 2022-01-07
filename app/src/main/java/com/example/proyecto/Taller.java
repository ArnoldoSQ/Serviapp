package com.example.proyecto;


import android.graphics.drawable.Drawable;

public class Taller {

    String text;
    int id;
    Drawable imagen;
    public String getText() {
        return text;
    }

    public Drawable getImagen() {
        return imagen;
    }


    public Taller(String text, Drawable imagen, int id) {
        this.text = text;
        this.imagen = imagen;
        this.id=id;
    }
}
