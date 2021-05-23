package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gymapp.Adaptador.RecyclerViewAdapter;
import com.example.gymapp.Model.Ejercicio;

import java.util.ArrayList;
import java.util.List;

public class ListaEjercicios2 extends AppCompatActivity {

    List<Ejercicio> ejercicioList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ejercicios2);

        initData();

        recyclerView = (RecyclerView)findViewById(R.id.listaEjercicios);
        adapter = new RecyclerViewAdapter(ejercicioList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){

        ejercicioList.add(new Ejercicio(R.drawable.pierna_uno, "Sentadilla con barra"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_dos, "Sentadilla con pesas"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_tres, "Zancada con mancuernas"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_cuatro, "Sentadilla sumo con mancuerna"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_cinnco, "Sentadilla con pesa rusa"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_seis, "Elevación pelvis"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_siete, "Patada trasera"));
        ejercicioList.add(new Ejercicio(R.drawable.pierna_ocho, "Levantamiento pierna lateral"));

    }

}
