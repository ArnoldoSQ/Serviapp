package com.example.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


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
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registar_db();
            }
        });
    }
    private void   registar_db(){
        try {
            System.out.println("1");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("2");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://192.168.0.6:3306/serviapp?user=root&password=Sonik011");
            System.out.println("3");
            Statement qwery= conexion.createStatement();
            System.out.println("4");
            String call="call registro_de_usuario(";
            call+="'"+correo.getText()+"',";
            call+="'"+usuario.getText()+"',";
            call+="'"+contraseña.getText()+"',";
            call+="'"+domicilio.getText()+"',";
            call+=telefono.getText()+" )";

            System.out.println(call);
            qwery.executeQuery(call);
            System.out.println("5");
        }catch (SQLException e){
            Toast.makeText(this, "error en la conexion "+e.toString(), Toast.LENGTH_LONG).show();
        }catch (ClassNotFoundException e){
            Toast.makeText(this, "error en la conexion "+e.toString(), Toast.LENGTH_LONG).show();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            Toast.makeText(this, "error en la conexion "+e.toString(), Toast.LENGTH_LONG).show();
        }


    }

}