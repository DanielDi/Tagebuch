package com.example.tagebuch.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tagebuch.memento.Memento;
import com.example.tagebuch.model.dao.MementoRoomDAO;
import com.example.tagebuch.model.dao.CategoriaRoomDAO;
import com.example.tagebuch.model.dao.PensamientoRoomDAO;
import com.example.tagebuch.model.pojo.Categoria;
import com.example.tagebuch.model.pojo.Pensamiento;

@Database(entities = {Pensamiento.class, Categoria.class, Memento.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {

  public abstract PensamientoRoomDAO pensamientoRoomDAO();
  public abstract CategoriaRoomDAO categoriaRoomDAO();
  public abstract MementoRoomDAO mementoRoomDAO();
  private  static LocalStorage localStorage;

  public static  LocalStorage getLocalStorage(final Context context) {
    if(localStorage == null) {
      localStorage = Room.databaseBuilder(
        context,
        LocalStorage.class,
        "DCPS2021-01-db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries() // Crear un hilo ejecución alterna (por facilidad lo hacemos acá, pero es mejor con otra librería).
        .build();
    }

    return localStorage;
  }

}
