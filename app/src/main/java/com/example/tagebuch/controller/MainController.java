package com.example.tagebuch.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.tagebuch.view.MainActivity;

public class MainController {

    public static void reportarPensamiento(MainActivity mainActivity,String titulo,
                                           String descripcion,String cat){

    }

    public static void editarPopup(MainActivity mainActivity){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setMessage("Selecciona una categoria:")


                .setPositiveButton("X", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
