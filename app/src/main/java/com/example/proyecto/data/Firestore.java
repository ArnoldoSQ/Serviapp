package com.example.proyecto.data;

import static com.google.android.gms.tasks.Tasks.await;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class  Firestore extends AsyncTask<Void, Void, Void> {
    private final FirebaseFirestore firestore;
    private final FirebaseAuth fireauth;
    private Map<String, Object> data;
    private final onFinish onFinish;
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private ProgressDialog progressDialog;
    private String newDocID, action, result;

    public Firestore(Context context, Firestore.onFinish onFinish) {
        this.context = context;
        firestore = FirebaseFirestore.getInstance();
        fireauth = FirebaseAuth.getInstance();
        this.onFinish = onFinish;
    }

    public void registrarUsuario(Usuario usuario, String newDocID){
        this.data = usuario.toMap();
        this.newDocID = newDocID;
        this.action = "Registrando usuario...";
    }

    public void iniciarSesion(String correo, String contraseña){
        this.data = new HashMap<>();
        this.data.put("correo", correo);
        this.data.put("contraseña", contraseña);
        this.action = "Ingresando...";
    }

    private Task<Void> registerUser(){
        try{
            String correo = Objects.requireNonNull(this.data.get("correo")).toString();
            String contraseña = Objects.requireNonNull(this.data.get("contraseña")).toString();

            await(this.firestore.collection("Usuario").document(this.newDocID).set(data));
            await(this.fireauth.createUserWithEmailAndPassword(correo,contraseña));

            this.result = "Cuenta creado con éxito";
        } catch (Exception e){
            this.result = "Error: " + e.toString();
        }

        return  null;
    }

    private Task<Void> tryLogin(){
        try{
            String correo = Objects.requireNonNull(this.data.get("correo")).toString();
            String contraseña = Objects.requireNonNull(this.data.get("contraseña")).toString();
            await(this.fireauth.signInWithEmailAndPassword(correo, contraseña));
            this.result = "Bienvenido";
        } catch (Exception e){
            this.result = "Error: " + e.toString();
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            if(this.action.equals("Registrando usuario...")){
                await(Objects.requireNonNull(this.registerUser()));
            } else if(this.action.equals("Ingresando...")) {
                await(Objects.requireNonNull(this.tryLogin()));
            }
        } catch (Exception ignored){

        }

        return null;
    }
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, this.action, "Por favor, espere...",true,false);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.setMessage("Terminando proceso...");
        progressDialog.dismiss();
        this.onFinish.firestoreFinish(this.result);
    }

    public interface onFinish {
        void firestoreFinish(String result);
    }
}