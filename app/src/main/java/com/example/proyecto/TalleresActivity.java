package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyecto.data.FirestoreRetrieve;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class TalleresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telleres);
        initList();
    }

    private void initList(){
        String categoria = getIntent().getStringExtra("categoria");

        FirestoreRetrieve firestore = new FirestoreRetrieve(this, result -> {
            for(int i = 0; i < result.size(); i++){
                System.out.println(result.get(i).getId());
            }
        });

        firestore.getColeccion("Usuario", true);
        firestore.execute();
    }
}