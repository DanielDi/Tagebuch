package com.example.tagebuch.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.MainActivity;

public class MainController {

    private PensamientoRoomDAO suscripcionRoomDAO;

    public void reportarPensamiento(MainActivity mainActivity, String titulo,
                                    String descripcion, String categoria){
        this.suscripcionRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAORoomDAO();
        Pensamiento pensamiento = new Pensamiento(titulo,descripcion,categoria);
        this.suscripcionRoomDAO.insertAll(pensamiento);
        System.out.println("PENSAMIENTO CREADO "+titulo+" "+descripcion);
    }

    public static Boolean verificarLongitud(String str){
        if(str.length()< 100){
            return true;
        }
        return false;
    }

    public static Boolean verificarTextoNoNull(String titulo, String descripcion){
        if(titulo.isEmpty() || descripcion.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

}
