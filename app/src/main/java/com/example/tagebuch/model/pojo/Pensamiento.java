package com.example.tagebuch.model.pojo;

import com.example.tagebuch.model.pojo.Categoria;

import java.util.Date;
import java.util.Calendar;

public class Pensamiento {
    private String titulo;
    private String descripcion;
    private Date fecha;
    private Categoria categoria;

    public Pensamiento(String titulo, String descripcion, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.setFecha();
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

    public void setFecha() {
        this.fecha = Calendar.getInstance().getTime();;
    }
}
