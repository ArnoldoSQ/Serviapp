package com.example.proyecto;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


public class  ModuloMensajes extends AsyncTask<Void, Void, Void> {
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final SmsManager smsManager;
    private ProgressDialog progressDialog;
    private String mensaje, numero;


    public ModuloMensajes(Context context) {
        this.context = context;
        this.smsManager = SmsManager.getDefault();
    }

    public void crearMensaje(String numero, String mensaje){
        this.numero = numero;
        this.mensaje = mensaje;
    }

    private void sendSMS(){
        this.smsManager.sendTextMessage(this.numero, null, this.mensaje, null, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected Void doInBackground(Void... voids) {
        if(this.context.checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        } else {
            Toast.makeText(context, "No se tienen persmisos de SMS.", Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Enviando mensajes.", "Por favor, espere...",true,false);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.setMessage("Terminando proceso...");
        progressDialog.dismiss();
        Toast.makeText(context,"Mensaje enviado",Toast.LENGTH_SHORT).show();
    }
}