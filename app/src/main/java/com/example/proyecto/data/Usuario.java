package com.example.proyecto.data;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    public String correo;
    public String usuario;
    public String contraseña;
    public String domicilio;
    public String telefono;

    public Usuario(){

    }

    public Usuario(String correo, String usuario, String contraseña, String domicilio, String telefono) {
        this.correo = correo;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("correo", this.correo);
        result.put("usuario", this.usuario);
        result.put("contraseña", this.contraseña);
        result.put("domicilio", this.domicilio);
        result.put("telefono", this.telefono);

        return result;
    }
}
