package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class AdaptadorTaller extends BaseAdapter {

    Context achu;
    List<Taller>nombre;

    public AdaptadorTaller(Context achu, List<Taller> nombre) {
        this.achu = achu;
        this.nombre = nombre;
    }

    @Override
    public int getCount() {
        return nombre.size();
    }

    @Override
    public Taller getItem(int position) {
        return nombre.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nombre.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nombreL;
        RelativeLayout nombreR;
        if(convertView == null){
            convertView = LayoutInflater.from(achu).inflate(R.layout.taller, null);
        };
        nombreL=convertView.findViewById(R.id.tectotaller);
        nombreR=convertView.findViewById(R.id.talleres);
        nombreL.setText(nombre.get(position).text);
        nombreR.setBackground(nombre.get(position).imagen);
        return convertView;
    }
}
