package com.example.tagebuch.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.tagebuch.memento.CareTaker;
import com.example.tagebuch.memento.Memento;
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
    private CareTaker caretaker = new CareTaker();

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

        pensamiento = this.pensamientoRoomDAO.obtenerUltimo();
        System.out.println("PENSAMIENTO CREADO "+titulo+" "+descripcion);

        Memento memento = new Memento(pensamiento.getId(), titulo, descripcion, pensamiento.getFecha(), categoria, "creado");
        System.out.println("ID PENSAMIENTO GUARDADO EN MEMENTO = "+pensamiento.getId());
        caretaker.add(mainActivity, memento);
        mainActivity.onRestart();
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
        Pensamiento pensamiento = this.pensamientoRoomDAO.obtenerPensamiento(id);
        Memento memento = new Memento(pensamiento.getId(), pensamiento.getTitulo(),
          pensamiento.getDescripcion(), pensamiento.getFecha(), pensamiento.getDescripcion(),
          "editado");

        caretaker.add(mainActivity, memento);

        this.pensamientoRoomDAO.editarPensamiento(titulo,descripcion,id);
        System.out.println("PENSAMIENTO EDITADO"+titulo+" "+descripcion);
        mainActivity.onRestart();
    }

    public void eliminarPensamiento(MainActivity mainActivity, int id){
        this.pensamientoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .pensamientoRoomDAO();
        Pensamiento pensamiento = this.pensamientoRoomDAO.obtenerPensamiento(id);
        Memento memento = new Memento(pensamiento.getId(), pensamiento.getTitulo(),
          pensamiento.getDescripcion(), pensamiento.getFecha(), pensamiento.getDescripcion(),
          "eliminado");

        this.pensamientoRoomDAO.eliminarPensamiento(id);
        caretaker.add(mainActivity, memento);

        System.out.println("PENSAMIENTO ELIMINADO ID "+ id);
        mainActivity.onRestart();
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

    public void retroceder(MainActivity mainActivity){
        Memento memento = caretaker.obtenerUltimoMemento(mainActivity);
        System.out.println("MEMENTO CASES");
        System.out.println(memento.getPosicion() + " || " + memento.getId() + " || " + memento.getAccion());
        switch (memento.getAccion()){
            case "creado":
                eliminarPensamiento(mainActivity, memento.getId());
            case "editado":
                editarPensamiento(mainActivity, memento.getTitulo(), memento.getDescripcion(), memento.getId());
            case "eliminado":
                reportarPensamiento(mainActivity, memento.getTitulo(), memento.getDescripcion(), memento.getCategoria());
            default:

        }
    }

}
