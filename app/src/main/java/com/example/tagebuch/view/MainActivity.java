package com.example.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tagebuch.R;
import com.example.tagebuch.controller.MainController;

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
                            Fragmento_pensamiento.newInstance("prueba"+i, this))
                    .commit();
        }

        Reportar = findViewById(R.id.home_activity_button_reportar);
        Reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainController
            }
        });
    }
}