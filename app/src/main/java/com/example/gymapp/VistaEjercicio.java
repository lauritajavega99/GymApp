package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymapp.Database.GymDB;
import com.example.gymapp.Utilidades.TimeLimit;

public class VistaEjercicio extends AppCompatActivity {

    int imagen_id;
    String nombre;

    TextView temporizador;
    TextView titulo;
    ImageView detalle_imagen;

    Button btnStart;

    boolean corriendo =false;
    GymDB gymDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_ejercicio);

        gymDB = new GymDB(this);
        temporizador = (TextView)findViewById(R.id.temporizador);
        titulo = (TextView)findViewById(R.id.titulo);
        detalle_imagen = (ImageView)findViewById(R.id.detalle_imagen);

        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!corriendo){
                    btnStart.setText("HECHO");

                    int timeLimit = 0;
                    if(gymDB.getSettingMode() == 0)
                        timeLimit = TimeLimit.TIME_LIMIT_FACIL;
                    else if(gymDB.getSettingMode() == 1)
                        timeLimit = TimeLimit.TIME_LIMIT_MEDIO;
                    else if(gymDB.getSettingMode() == 2)
                        timeLimit = TimeLimit.TIME_LIMIT_DIFICIL;

                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long l) {
                            temporizador.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(VistaEjercicio.this,"¡Se acabó!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else {
                    Toast.makeText(VistaEjercicio.this,"¡Se acabó!",Toast.LENGTH_SHORT).show();
                    finish();
                }

                corriendo = !corriendo;
            }
        });

        temporizador.setText("");

        if(getIntent()!=null){

            imagen_id = getIntent().getIntExtra("imagen_id", -1);
            nombre = getIntent().getStringExtra("nombre");

            detalle_imagen.setImageResource(imagen_id);
            titulo.setText(nombre);

        }
    }
}