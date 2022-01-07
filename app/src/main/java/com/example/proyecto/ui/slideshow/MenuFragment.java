package com.example.proyecto.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyecto.R;
import com.example.proyecto.telleres;
import com.example.proyecto.taller;
import com.example.proyecto.daptador_taller;

public class MenuFragment extends Fragment implements View.OnClickListener {
    ImageButton electricidad, plomeria , carpinteria, herreria, jardineria, contruccion,mecanica,limpieza;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

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
        Intent intent = new Intent(getContext(), telleres.class);
        String achu = "ninguno";

        int id=v.getId();
        if(electricidad.getId()==id){
            achu="electricidad";
        }else if (plomeria.getId()==id){
            achu="plomeria";
        }else if(carpinteria.getId()==id){
            achu="carpinteria";
        }else if(herreria.getId()==id){
            achu="herreria";
        }else if (jardineria.getId()==id){
            achu="jardineria";
        }else if (contruccion.getId()==id){
            achu="contruccion";
        }else if (mecanica.getId()==id){
            achu="mecanica";
        }else if (limpieza.getId()==id){
            achu="limpieza";
        }

        Toast.makeText(getContext(), achu, Toast.LENGTH_SHORT).show();
    }

}