package com.example.tagebuch.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.tagebuch.model.pojo.Pensamiento;

@Dao
public interface PensamientoRoomDAO {
    @Insert
    void insertAll(Pensamiento...pensamientos);
}
