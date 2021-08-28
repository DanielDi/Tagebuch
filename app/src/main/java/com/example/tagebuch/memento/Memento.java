package com.example.tagebuch.memento;

import com.example.tagebuch.model.pojo.Pensamiento;

public class Memento {

    private Pensamiento pen;
    private String accion;

    public Memento(Pensamiento pen, String accion) {
        this.pen = pen;
        this.accion = accion;
    }

    public Pensamiento getPen() {
        return pen;
    }

    public void setPen(Pensamiento pen) {
        this.pen = pen;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
}
