package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gymapp.Adaptador.RecyclerViewAdapter;
import com.example.gymapp.Model.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class ListaEjercicios1 extends AppCompatActivity  {

    List<Ejercicio> ejercicioList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ejercicios1);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.listaEjercicios);
        adapter = new RecyclerViewAdapter(ejercicioList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){

        ejercicioList.add(new Ejercicio(R.drawable.brazo_uno, "Triceps patada"));
        ejercicioList.add(new Ejercicio(R.drawable.brazo_dos, "Flexiones"));
        ejercicioList.add(new Ejercicio(R.drawable.brazo_tres, "Fondos de triceps"));
        ejercicioList.add(new Ejercicio(R.drawable.brazo_cuatro, "Press de hombros"));
        ejercicioList.add(new Ejercicio(R.drawable.brazo_cinco, "Biceps curl"));
        ejercicioList.add(new Ejercicio(R.drawable.brazo_seis, "Plancha con mancuerna"));
    }
}
