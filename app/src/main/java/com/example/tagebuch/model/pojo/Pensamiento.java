package com.example.tagebuch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.tagebuch.model.pojo.Categoria;

import java.util.Date;
import java.util.Calendar;

@Entity(tableName = "pensamientos")
public class Pensamiento {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String categoria;

    public Pensamiento(String titulo, String descripcion, String categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = Calendar.getInstance().getTime().toString();
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
