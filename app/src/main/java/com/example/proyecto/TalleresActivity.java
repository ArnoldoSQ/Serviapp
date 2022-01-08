package com.example.proyecto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyecto.data.FirestoreRetrieve;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TalleresActivity extends AppCompatActivity {
    public ListView listViewTalleres;
    public AdaptadorTaller adaptadorTaller;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telleres);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(){
        String categoria = getIntent().getStringExtra("categoria");
        Drawable fondo = getFondo(categoria);

        FirestoreRetrieve firestore = new FirestoreRetrieve(this, result -> {
            List<Taller> talleres = new ArrayList<>();

            for(int i = 0; i < result.size(); i++){
                talleres.add(new Taller(
                    i,
                    Objects.requireNonNull(result.get(i).get("nombre")).toString(),
                    Objects.requireNonNull(result.get(i).get("telefono")).toString(),
                    fondo
                ));
            }

            initList(talleres);
        });

        firestore.getColeccion(categoria, true);
        firestore.execute();
    }

    private void initList(List<Taller> talleres){
        this.adaptadorTaller = new AdaptadorTaller(this, talleres);
        this.listViewTalleres = findViewById(R.id.lista_talleres);
        this.listViewTalleres.setAdapter(adaptadorTaller);

        listViewTalleres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogoMensaje dialogo = new DialogoMensaje((telefono, mensaje) -> {
                    crearMensaje(telefono, mensaje);
                });

                Bundle b = new Bundle();
                b.putString("telefono", adaptadorTaller.getItem(i).telefono);
                dialogo.setArguments(b);
                dialogo.show(getSupportFragmentManager(), "Mensajeria");
            }
        });
    }

    private void crearMensaje(String telefono, String mensaje){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();

        FirestoreRetrieve firestore = new FirestoreRetrieve(this, result -> {
            Map<String, Object> usuario = result.get(0).getData();
            String nombre = usuario.get("usuario").toString();
            String domicilio = usuario.get("domicilio").toString();

            enviarMensaje(telefono, mensaje, nombre, domicilio);
        });

        firestore.getDocument("Usuario", email, false);
        firestore.execute();
    }

    private void enviarMensaje(String telefono, String problema, String nombre, String domicilio){
        String mensaje = "Nombre: " + nombre + "\n";
        mensaje += "Domicilio: " + domicilio + "\n\n";
        mensaje += "Problema: " + problema;

        ModuloMensajes mensajeria = new ModuloMensajes(this);
        mensajeria.crearMensaje(telefono, mensaje);
        mensajeria.execute();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Drawable getFondo(String cat){
        if(cat.equals("Electricista ")){
            return getDrawable(R.drawable.electricista);
        } else if(cat.equals("Plomeria")){
            return getDrawable(R.drawable.plomeria);
        } else if(cat.equals("Carpinteria")){
            return getDrawable(R.drawable.carpinteria);
        } else if(cat.equals("Herreria")){
            return getDrawable(R.drawable.herreria);
        } else if(cat.equals("Jardineria")){
            return getDrawable(R.drawable.jardineria);
        } else if(cat.equals("Construccion")){
            return getDrawable(R.drawable.construccion);
        } else if(cat.equals("Mecanica")){
            return getDrawable(R.drawable.mecanica);
        } else {
            return getDrawable(R.drawable.limpieza);
        }
    }
}