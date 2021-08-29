package com.example.tagebuch.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainController;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.shared.PopUp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button Reportar;
    private ImageButton retroceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity contex = this;
        List<Pensamiento> pensamientos = new MainController().cargarPensamientos(this);

        for (Pensamiento pensamiento: pensamientos) {
            String titulo = pensamiento.getTitulo();
            String descripcion = pensamiento.getDescripcion();
            int id = pensamiento.getId();
            String categoria = pensamiento.getCategoria();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(
                            R.id.liner_layout_home_activity,
                            Fragmento_pensamiento.newInstance(titulo, descripcion, id, this)

                    ).commit();
        }

        Reportar = findViewById(R.id.home_activity_button_reportar);
        Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarCategoria(contex);
            }
        });

        retroceder = findViewById(R.id.undo_action_button);
        retroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainController().retroceder(contex);
                contex.onRestart();
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
//                        mainActivity.onRestart();
                    }else{
                        PopUp.mostrarPopUp(mainActivity,"El titulo no puede superar " +
                                "los 100 caracteres");
                    }
                }else{
                    PopUp.mostrarPopUp(mainActivity,"El título y la descripción no deben estar vacíos");
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
//                        mainActivity.onRestart();
                    }else{
                        PopUp.mostrarPopUp(mainActivity,"El titulo no puede superar " +
                        "los 100 caracteres");
                    }
                }else{
                    PopUp.mostrarPopUp(mainActivity,"El título y la descripción no deben " +
                    "estar vacíos");
                }
            }
        });
        builder.show();
    }

    public void eliminarPensamiento(MainActivity mainActivity, int id){
        new MainController().eliminarPensamiento(mainActivity, id);
//        mainActivity.onRestart();
    }

    public static void crearCategorias(MainActivity mainActivity){
        if(new MainController().tablaVacia(mainActivity)){
            new MainController().creacionCategorias(mainActivity);
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }
}