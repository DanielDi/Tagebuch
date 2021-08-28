package com.example.tagebuch.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.CategoriaRoomDAO;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.view.MainActivity;

import java.util.List;

public class MainController {

    private PensamientoRoomDAO pensamientoRoomDAO;
    private CategoriaRoomDAO categoriaRoomDAO;

    public List<Pensamiento> cargarPensamientos(MainActivity mainActivity){
        this.pensamientoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAO();
        return this.pensamientoRoomDAO.cargarPensamientos();
    }

    public void reportarPensamiento(MainActivity mainActivity, String titulo,
                                    String descripcion, String categoria){
        this.pensamientoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAO();
        Pensamiento pensamiento = new Pensamiento(titulo,descripcion,categoria);
        this.pensamientoRoomDAO.insertAll(pensamiento);
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

    public void creacionCategorias(MainActivity mainActivity){
        this.categoriaRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .categoriaRoomDAO();
        Categoria cat1 = new Categoria("Amor","Sentimiento de amor","#FF0000");
        Categoria cat2 = new Categoria("Trabajo","ideas del trabajo","#FFFF00");
        Categoria cat3 = new Categoria("Chistes","Chistes nuevos","#008000");
        Categoria cat4 = new Categoria("Desahogo","Desahogate Bro","67A1CF");
        this.categoriaRoomDAO.insertAll(cat1, cat2, cat3, cat4);
        System.out.println("CATEGORIAS CREADAS");
    }

    public void editarPensamiento(MainActivity mainActivity,String titulo,String descripcion,int id){
        this.pensamientoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAO();
        this.pensamientoRoomDAO.editarPensamiento(titulo,descripcion,id);
        System.out.println("PENSAMIENTO EDITADO"+titulo+" "+descripcion);
    }

    public void eliminarPensamiento(MainActivity mainActivity, int id){
        this.pensamientoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAO();
        this.pensamientoRoomDAO.eliminarPensamiento(id);
        System.out.println("PENSAMIENTO ELIMINADO ");
    }

    public boolean tablaVacia(MainActivity mainActivity){
        this.categoriaRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .categoriaRoomDAO();
        Integer num = this.categoriaRoomDAO.tablaVacia();
        if (num > 0){
            return false;
        }
        return true;
    }

}
