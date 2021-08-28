package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tagebuch.memento.Memento;

@Dao
public interface MementoRoomDAO {
    @Insert
    void insertAll(Memento...memento);

    @Query("SELECT * FROM mementos ORDER BY posicion DESC LIMIT 1")
    Memento obtenerUltimo();
}
