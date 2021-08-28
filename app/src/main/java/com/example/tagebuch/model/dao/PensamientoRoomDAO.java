package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Pensamiento;

@Dao
public interface PensamientoRoomDAO {
    @Insert
    void insertAll(Pensamiento...pensamientos);

    @Query("UPDATE pensamientos SET titulo=:tituloQ, descripcion = :descripcionQ " +
            "WHERE id = :idQ")
    void editarPensamiento(String tituloQ,String descripcionQ, int idQ);
}
