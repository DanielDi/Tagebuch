package com.example.tagebuch.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainController;
import com.example.tagebuch.model.pojo.Pensamiento;
import com.example.tagebuch.shared.PopUp;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private Button Reportar;

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
        MainActivity.crearCategorias(this);
        
        getSupportActionBar().hide();
    }

    public void seleccionarCategoria(MainActivity mainActivity){
        String[] selectedItems = {"Amor","Trabajo","Chistes","Desahogo"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
//        final View customLayout = getLayoutInflater().inflate(R.layout.categories_selection, null);

//        builder.setView(customLayout);

        Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.categories_selection);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RadioButton amor = dialog.findViewById(R.id.amor_radio_button);
        RadioButton trabajo = dialog.findViewById(R.id.trabajo_radio_button);
        RadioButton chistes = dialog.findViewById(R.id.chistes_radio_button);
        RadioButton desahogo = dialog.findViewById(R.id.desahogo_radio_button);

        Button sendButton = dialog.findViewById(R.id.send_selected_category_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("-------------------------");
                System.out.println(amor.isChecked());
                System.out.println("-------------------------");
                System.out.println(trabajo.isChecked());
                System.out.println("------------------------------");
                System.out.println(chistes.isChecked());
                System.out.println("-------------------------------");
                System.out.println(desahogo.isChecked());

                int selectedPosition = 0;
                if (amor.isChecked()) {
                    selectedPosition = 0;
                } else if (trabajo.isChecked()) {
                    selectedPosition = 1;
                } else if (chistes.isChecked()) {
                    selectedPosition = 2;
                } else if (desahogo.isChecked()) {
                    selectedPosition = 3;
                }
                dialog.dismiss();
                System.out.println(selectedPosition);
                crearPensamiento(mainActivity, selectedItems[selectedPosition]);
            }
        });

        dialog.show();
//        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                System.out.println("-------------------------");
//                System.out.println(amor.isChecked());
//                System.out.println("-------------------------");
//                System.out.println(trabajo.isChecked());
//                System.out.println("------------------------------");
//                System.out.println(chistes.isChecked());
//                System.out.println("-------------------------------");
//                System.out.println(desahogo.isChecked());
//
//                int selectedPosition = 0;
//                if (amor.isChecked()) {
//                    selectedPosition = 0;
//                } else if (trabajo.isChecked()) {
//                    selectedPosition = 1;
//                } else if (chistes.isChecked()) {
//                    selectedPosition = 2;
//                } else if (desahogo.isChecked()) {
//                    selectedPosition = 3;
//                }
//                dialogInterface.dismiss();
//                System.out.println(selectedPosition);
//                crearPensamiento(mainActivity, selectedItems[selectedPosition]);
//
//            }
//        }).show();

        ImageView imageViewClose = dialog.findViewById(R.id.close_pop_up);

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//              Toast.makeText(HomeEntryActivity.this, "Dialog close", Toast.LENGTH_SHORT).show();
            }
        });
//        Button amor = customLayout.findViewById(R.id.amor_button);
//        Button trabajo = customLayout.findViewById(R.id.trabajo_button);
//        Button chistes = customLayout.findViewById(R.id.chistes_button);
//        Button desahogo = customLayout.findViewById(R.id.desahogo_button);
//
//        amor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (amor.getText().equals(selectedItems[0])) {
//                    System.out.println(amor.getText());
//                    crearPensamiento(mainActivity, selectedItems[0]);
//                }
//            }
//        });
//        trabajo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (trabajo.getText().equals(selectedItems[1])) {
//                    System.out.println(trabajo.getText());
//                    crearPensamiento(mainActivity, selectedItems[1]);
//                }
//            }
//        });
//        chistes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (chistes.getText().equals(selectedItems[2])) {
//                    System.out.println(chistes.getText());
//                    crearPensamiento(mainActivity, selectedItems[2]);
//                }
//            }
//        });
//        desahogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (desahogo.getText().equals(selectedItems[3])) {
//                    System.out.println(desahogo.getText());
//                    crearPensamiento(mainActivity, selectedItems[3]);
//                }
//            }
//        });
        // Set the dialog title
//        builder.setTitle("SELECCIONA UNA CATEGORIA:");
//        builder.setSingleChoiceItems(selectedItems, 0, null)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        dialog.dismiss();
//                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
//                        System.out.println(selectedPosition);
//                        crearPensamiento(mainActivity,selectedItems[selectedPosition]);
//                    }
//                })
//                .show();

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
                        mainActivity.onRestart();
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
                        mainActivity.onRestart();
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
        mainActivity.onRestart();
    }

    public static void crearCategorias(MainActivity mainActivity){
        if(new MainController().tablaVacia(mainActivity)){ //Consulta si tabla grupos esta vacia
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