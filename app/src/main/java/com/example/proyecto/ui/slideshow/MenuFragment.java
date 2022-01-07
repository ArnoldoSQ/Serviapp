package com.example.proyecto.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyecto.R;
import com.example.proyecto.TalleresActivity;

public class MenuFragment extends Fragment implements View.OnClickListener {
    ImageButton electricidad, plomeria , carpinteria, herreria, jardineria, contruccion,mecanica,limpieza;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        electricidad=root.findViewById(R.id.button1);
        plomeria=root.findViewById(R.id.button2);
        carpinteria=root.findViewById(R.id.button3);
        herreria=root.findViewById(R.id.button4);
        jardineria=root.findViewById(R.id.button5);
        contruccion=root.findViewById(R.id.button6);
        mecanica=root.findViewById(R.id.button7);
        limpieza=root.findViewById(R.id.button8);

        electricidad.setOnClickListener(this);
        plomeria.setOnClickListener(this);
        carpinteria.setOnClickListener(this);
        herreria.setOnClickListener(this);
        jardineria.setOnClickListener(this);
        contruccion.setOnClickListener(this);
        mecanica.setOnClickListener(this);
        limpieza.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TalleresActivity.class);
        String categoria = "NULL";

        int id=v.getId();
        if(electricidad.getId()==id){
            categoria="Electricidad";
        }else if (plomeria.getId()==id){
            categoria="Plomeria";
        }else if(carpinteria.getId()==id){
            categoria="Carpinteria";
        }else if(herreria.getId()==id){
            categoria="Herreria";
        }else if (jardineria.getId()==id){
            categoria="Jardineria";
        }else if (contruccion.getId()==id){
            categoria="Contruccion";
        }else if (mecanica.getId()==id){
            categoria="Mecanica";
        }else if (limpieza.getId()==id){
            categoria="Limpieza";
        }

        intent.putExtra("categoria", categoria);
        startActivity(intent);
    }

}