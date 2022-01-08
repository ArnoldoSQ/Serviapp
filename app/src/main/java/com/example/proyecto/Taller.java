package com.example.proyecto;


import android.graphics.drawable.Drawable;

public class Taller {


    int id;
    String text;
    String telefono;
    Drawable imagen;

    public String getText() {
        return text;
    }
    public Drawable getImagen() {
        return imagen;
    }

    public Taller(int id, String text, String telefono, Drawable imagen) {
        this.text = text;
        this.telefono = telefono    ;
        this.imagen = imagen;
        this.id=id;
    }
}
