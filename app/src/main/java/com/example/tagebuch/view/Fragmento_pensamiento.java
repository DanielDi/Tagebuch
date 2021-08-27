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

public class Fragmento_pensamiento extends Fragment {

    private String tituloPensamiento;
    private View rootView;
    private TextView tituloPensamientoTextView;
    private Button but_editar;
    private Button but_eliminar;

    public Fragmento_pensamiento() {

    }

    public static Fragmento_pensamiento newInstance(String tituloPensamiento, MainActivity mainActivity) {
        Fragmento_pensamiento fragment = new Fragmento_pensamiento();
        fragment.setTituloPensamiento(tituloPensamiento);
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

}
