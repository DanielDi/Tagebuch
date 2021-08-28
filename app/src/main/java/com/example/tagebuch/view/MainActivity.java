package com.example.tagebuch.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainController;
import com.example.tagebuch.shared.PopUp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button Reportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity contex = this;

        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(
                            R.id.liner_layout_home_activity,
                            Fragmento_pensamiento.newInstance("prueba"+i,"sapo",2,this))
                                    .commit();
        }

        Reportar = findViewById(R.id.home_activity_button_reportar);
        Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarCategoria(contex);
            }
        });
        MainActivity.crearCategorias(this);
    }

    public void seleccionarCategoria(MainActivity mainActivity){
        String[] selectedItems = {"Amor","Trabajo","Chistes","Desahogo"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        // Set the dialog title
        builder.setTitle("SELECCIONA UNA CATEGORIA:");
        builder.setSingleChoiceItems(selectedItems, 0, null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        System.out.println(selectedPosition);
                        crearPensamiento(mainActivity,selectedItems[selectedPosition]);
                    }
                })
                .show();

    }

    public void crearPensamiento(MainActivity mainActivity,String categoria){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        // Set the dialog title

        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View layout= inflater.inflate(R.layout.reportar_pensamiento, null);
        builder.setView(layout);

        builder.setTitle("INGRESA LOS DATOS DEL PENSAMIENTO:");

        EditText titulo = layout.findViewById(R.id.reportar_pensamiento_titulo_text_edit);

        EditText descripcion = layout.findViewById(R.id.reportar_pensamiento_descripcion_text_edit);

        builder.setPositiveButton("Reportar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                String tituloS = titulo.getText().toString();
                String descripcionS = descripcion.getText().toString();
                if(MainController.verificarTextoNoNull(tituloS,descripcionS)){
                    if(MainController.verificarLongitud(tituloS)){
                        new MainController().reportarPensamiento(mainActivity,tituloS,descripcionS,categoria);
                    }else{
                        //PopUp.mostrarPopUp(mainActivity,"El titulo no puede superar " +
                               // "los 100 caracteres","");
                    }
                }else{
                    //PopUp.mostrarPopUp(mainActivity,"El título y la descripción no deben " +
                            //"estar vacíos","");
                }
            }
        });
        builder.show();
    }

    public void editarPensamiento(MainActivity mainActivity,int idP,String tituloP,String descripcionP){
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        // Set the dialog title

        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View layout= inflater.inflate(R.layout.reportar_pensamiento, null);
        builder.setView(layout);

        builder.setTitle("INGRESA LOS DATOS A EDITAR DEL PENSAMIENTO:");

        EditText titulo = layout.findViewById(R.id.reportar_pensamiento_titulo_text_edit);
        titulo.setText(tituloP);

        EditText descripcion = layout.findViewById(R.id.reportar_pensamiento_descripcion_text_edit);
        descripcion.setText(descripcionP);

        builder.setPositiveButton("Guardar",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                String tituloS = titulo.getText().toString();
                String descripcionS = descripcion.getText().toString();
                if(MainController.verificarTextoNoNull(tituloS,descripcionS)){
                    if(MainController.verificarLongitud(tituloS)){
                        new MainController().editarPensamiento(mainActivity,tituloS,descripcionS,idP);
                    }else{
                        //PopUp.mostrarPopUp(mainActivity,"El titulo no puede superar " +
                        // "los 100 caracteres","");
                    }
                }else{
                    //PopUp.mostrarPopUp(mainActivity,"El título y la descripción no deben " +
                    //"estar vacíos","");
                }
            }
        });
        builder.show();
    }

    public static void crearCategorias(MainActivity mainActivity){
        if(new MainController().tablaVacia(mainActivity)){ //Consulta si tabla grupos esta vacia
            new MainController().creacionCategorias(mainActivity);
        }
    }
}