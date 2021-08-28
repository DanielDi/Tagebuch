package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tagebuch.model.pojo.Categoria;

@Dao
public interface CategoriaRoomDAO {
    @Insert
    void insertAll(Categoria... categorias);

    @Query("SELECT * FROM categorias WHERE nombre = :catNombreQ")
    Categoria buscarCat(String catNombreQ);

    @Query("SELECT count(*) FROM categorias")
    Integer tablaVacia();
}
