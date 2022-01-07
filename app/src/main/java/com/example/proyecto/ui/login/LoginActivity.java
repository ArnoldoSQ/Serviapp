package com.example.proyecto.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Perfil_usuario;
import com.example.proyecto.R;
import com.example.proyecto.Registroactiviti;
import com.example.proyecto.data.Firestore;
import com.example.proyecto.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);
        Button registrar=findViewById(R.id.ingresar_r);

        registrar.setOnClickListener(v -> iniciarRegistro());

        loginButton.setOnClickListener(v -> iniciarSesion());
    }


    private void iniciarSesion(){
        Firestore firestore = new Firestore(this, result -> {
            Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();

            if(result.equals("Bienvenido")) {
                Intent inte= new Intent(getBaseContext(), Perfil_usuario.class);
                startActivity(inte);
            }
        });

        firestore.iniciarSesion(this.username.getText().toString(), this.password.getText().toString());
        firestore.execute();
    }

    private void iniciarRegistro(){
        Intent inte= new Intent(this, Registroactiviti.class);
        startActivity(inte);
    }
}