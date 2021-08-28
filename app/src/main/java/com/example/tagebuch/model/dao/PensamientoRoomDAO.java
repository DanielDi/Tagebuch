package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tagebuch.model.pojo.Pensamiento;

import java.util.List;

@Dao
public interface PensamientoRoomDAO {
    @Query("SELECT * FROM pensamientos")
    List<Pensamiento> cargarPensamientos();

    @Insert
    void insertAll(Pensamiento...pensamientos);

    @Query("UPDATE pensamientos SET titulo=:tituloQ, descripcion = :descripcionQ " +
            "WHERE id = :idQ")
    void editarPensamiento(String tituloQ,String descripcionQ, int idQ);

    @Query("DELETE FROM pensamientos WHERE id=:idQ")
    void eliminarPensamiento(int idQ);
}
