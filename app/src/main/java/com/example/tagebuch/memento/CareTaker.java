package com.example.tagebuch.memento;

import com.example.tagebuch.model.LocalStorage;
import com.example.tagebuch.model.dao.MementoRoomDAO;
import com.example.tagebuch.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private List<Memento> mementosList = new ArrayList<Memento>();
    private MementoRoomDAO mementoRoomDAO;

    public void add(MainActivity mainActivity, Memento memento){
        mementoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .mementoRoomDAO();
        mementoRoomDAO.insertAll(memento);

        Memento memento1 = mementoRoomDAO.obtenerUltimo();
        System.out.println(memento1.getPosicion() + " " + memento1.getAccion() + " " + memento1.getTitulo());
    }

    public Memento get(int index){
        return  mementosList.get(index);
    }

    public Memento obtenerUltimoMemento(MainActivity mainActivity){
        mementoRoomDAO = LocalStorage.getLocalStorage(mainActivity)
                .mementoRoomDAO();
        Memento memento = mementoRoomDAO.obtenerUltimo();
        return memento;
    }
}
