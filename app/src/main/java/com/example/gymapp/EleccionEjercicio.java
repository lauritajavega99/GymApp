package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EleccionEjercicio extends AppCompatActivity {

    ImageView imgAbs, imgBrazo, imgPierna, imgStrech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleccion_ejercicio);

        imgAbs = (ImageView)findViewById(R.id.imgAbs);
        imgBrazo = (ImageView)findViewById(R.id.imgBrazo);
        imgPierna = (ImageView)findViewById(R.id.imgPierna);
        imgStrech = (ImageView)findViewById(R.id.imgStrech);

        imgAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EleccionEjercicio.this,ListaEjercicios.class);
                startActivity(intent);
            }
        });

        imgBrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EleccionEjercicio.this,ListaEjercicios1.class);
                startActivity(intent);
            }
        });

        imgPierna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EleccionEjercicio.this,ListaEjercicios2.class);
                startActivity(intent);
            }
        });

        imgStrech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EleccionEjercicio.this,ListaEjercicios3.class);
                startActivity(intent);
            }
        });






    }
}