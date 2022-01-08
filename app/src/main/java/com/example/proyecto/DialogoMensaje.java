package com.example.proyecto;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;


public class DialogoMensaje extends AppCompatDialogFragment {
    private MensajeListener listener;

    public DialogoMensaje(MensajeListener listener){
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_mensaje, null);
        EditText txtMensaje = view.findViewById(R.id.problema);

        if(getContext().checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        builder.setView(view)
            .setTitle("Enviar problema.")
            .setNegativeButton("Cancelar", (dialog, which) -> {})
            .setPositiveButton("Enviar", (dialog, which) -> {
                String numero = requireArguments().getString("telefono");
                String mensaje = txtMensaje.getText().toString();

                listener.getDatos(numero, mensaje);
            }
        );

        return builder.create();
    }

    public interface MensajeListener{
        void getDatos(String telefono, String mensaje);
    }
}