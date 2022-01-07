package com.example.proyecto.data;

import static com.google.android.gms.tasks.Tasks.await;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;


public class  FirestoreRetrieve extends AsyncTask<Void, Void, Void> {
    private final FirebaseFirestore firestore;
    private final FirebaseAuth fireauth;
    private final onFinish onFinish;
    private Map<String, Object> data;
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private ProgressDialog progressDialog;
    private boolean showDialog;
    private String action, collectionID, documentID;
    private List<DocumentSnapshot> result;

    public FirestoreRetrieve(Context context, FirestoreRetrieve.onFinish onFinish) {
        this.context = context;
        firestore = FirebaseFirestore.getInstance();
        fireauth = FirebaseAuth.getInstance();
        this.onFinish = onFinish;
    }

    public void getColeccion(String coleccion, Boolean mostrarDialogo){
        this.action = "collection";
        this.collectionID = coleccion;
        this.showDialog = mostrarDialogo;
    }

    public void getDocument(String coleccion, String documento, Boolean mostrarDialogo){
        this.action = "document";
        this.collectionID = coleccion;
        this.documentID = documento;
        this.showDialog = mostrarDialogo;
    }

    private Task<Void> getCollection(){
        try {
            QuerySnapshot reference = await(this.firestore.collection(this.collectionID).get());
            this.result = reference.getDocuments();
        } catch (Exception e) {
            Toast.makeText(context, "Ocurrió un error inesperado. " + e.toString(), Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            if(this.action.equals("collection")) {
                await(Objects.requireNonNull(getCollection()));
            }
        } catch (Exception ignore){ }

        return null;
    }
    protected void onPreExecute() {
        super.onPreExecute();
        if(this.showDialog){
            progressDialog = ProgressDialog.show(context, "Consultando base de datos...", "Por favor, espere...",true,false);
        }
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(this.showDialog){
            progressDialog.setMessage("Terminando proceso...");
            progressDialog.dismiss();
        }

        this.onFinish.firestoreFinish(this.result);
    }

    public interface onFinish {
        void firestoreFinish(List<DocumentSnapshot> result);
    }
}