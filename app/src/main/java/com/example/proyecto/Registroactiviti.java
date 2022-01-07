package com.example.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.data.Firestore;
import com.example.proyecto.data.Usuario;

public class Registroactiviti extends AppCompatActivity {
    public EditText correo , usuario,contraseña,re_contraseña,domicilio,telefono;
    public Button registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registroactiviti);
        correo=findViewById(R.id.registro_correo);
        usuario=findViewById(R.id.registro_usuario);
        contraseña=findViewById(R.id.registro_contraseña);
        re_contraseña=findViewById(R.id.registro_re_contraseña);
        domicilio=findViewById(R.id.registro_domicilio);
        telefono=findViewById(R.id.registro_telefono);
        registrar=findViewById(R.id.registro_registrar);

        registrar.setOnClickListener(v -> registar_db());
    }

    public void registar_db(){
        if(formularioValido()){
            Usuario nuevo = new Usuario(
                    this.correo.getText().toString(),
                    this.usuario.getText().toString(),
                    this.contraseña.getText().toString(),
                    this.domicilio.getText().toString(),
                    this.telefono.getText().toString());

            Firestore firestore = new Firestore(this, new Firestore.onFinish() {
                @Override
                public void firestoreFinish(String result) {
                    Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
                    finishActivity(0);
                }
            });

            firestore.registrarUsuario(nuevo, nuevo.correo);
            firestore.execute();
        }
    }

    private Boolean formularioValido(){
        if(this.correo.getText().length() == 0){ //Si no ingresó un correo
            return false;
        }

        return true;
    }

}