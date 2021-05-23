package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Button btnEjercicios, btnOpciones, btnCalendario, btnTraining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEjercicios = findViewById(R.id.btnEjercicios);
        btnOpciones = (Button)findViewById(R.id.btnOpciones);
        btnTraining = (Button)findViewById(R.id.btnTraining);
        btnCalendario = (Button)findViewById(R.id.btnCalendario);

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Entreno_Diario.class);
                startActivity(intent);


            }
        });

        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendario.class);
                startActivity(intent);
            }
        });

        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Opciones.class);
                startActivity(intent);


            }
        });

        btnEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EleccionEjercicio.class);
                startActivity(intent);


            }
        });
    }
}