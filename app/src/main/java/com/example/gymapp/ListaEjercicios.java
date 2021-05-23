package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gymapp.Adaptador.RecyclerViewAdapter;
import com.example.gymapp.Model.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class ListaEjercicios extends AppCompatActivity {

    List<Ejercicio> ejercicioList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ejercicios);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.listaEjercicios);
        adapter = new RecyclerViewAdapter(ejercicioList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        ejercicioList.add(new Ejercicio(R.drawable.abs_uno, "V - SIT"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_dos, "Bicicleta"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_tres, "Giro Ruso"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_cuatro, "Abdominales simples"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_cinco, "Plancha lateral"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_seis, "Crunch inversos"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_siete, "Plancha"));
        ejercicioList.add(new Ejercicio(R.drawable.abs_ocho, "Piernas elevadas"));

    }
}