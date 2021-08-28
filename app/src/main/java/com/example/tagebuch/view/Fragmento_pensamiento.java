package com.example.tagebuch.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainController;

public class Fragmento_pensamiento extends Fragment {

    private String tituloPensamiento;
    private String descripcion;
    private int idP;
    private View rootView;
    private TextView tituloPensamientoTextView;
    private Button but_editar;
    private Button but_eliminar;
    private MainActivity contex;

    public Fragmento_pensamiento() {

    }

    public static Fragmento_pensamiento newInstance(String tituloPensamiento,String descriopcion,
                                                    int idP,MainActivity mainActivity) {
        Fragmento_pensamiento fragment = new Fragmento_pensamiento();
        fragment.setTituloPensamiento(tituloPensamiento);
        fragment.setDescripcion(descriopcion);
        fragment.setIdP(idP);
        fragment.setContex(mainActivity);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragmento_pensamiento, container, false);
        tituloPensamientoTextView = rootView.findViewById(R.id.fragmento_pensamiento_text_view);
        tituloPensamientoTextView.setText(this.tituloPensamiento);

        but_editar = (Button) rootView.findViewById(R.id.fragmento_button_editar);
        but_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(but_editar.getText());
                new MainActivity().editarPensamiento(contex,idP,tituloPensamiento,descripcion);
            }
        });

        but_eliminar = (Button) rootView.findViewById(R.id.fragmento_button_eliminar);
        but_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(but_eliminar.getText());
            }
        });
        return rootView;
    }

    public String getTituloPensamiento() {
        return tituloPensamiento;
    }

    public void setTituloPensamiento(String tituloPensamiento) {
        this.tituloPensamiento = tituloPensamiento;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public MainActivity getContex() {
        return contex;
    }

    public void setContex(MainActivity contex) {
        this.contex = contex;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
