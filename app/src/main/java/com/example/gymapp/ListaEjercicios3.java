package com.example.gymapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gymapp.Adaptador.RecyclerViewAdapter;
import com.example.gymapp.Model.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class ListaEjercicios3 extends AppCompatActivity {

    List<Ejercicio> ejercicioList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ejercicios3);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.listaEjercicios);
        adapter = new RecyclerViewAdapter(ejercicioList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){

        ejercicioList.add(new Ejercicio(R.drawable.strech_uno, "Estiramiento 1"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_dos, "Estiramiento 2"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_tres, "Estiramiento 3"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_cuatro, "Estiramiento 4"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_cinco, "Estiramiento 5"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_seis, "Estiramiento 6"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_siete, "Estiramiento 7"));
        ejercicioList.add(new Ejercicio(R.drawable.strech_ocho, "Estiramiento 8"));
    }
}