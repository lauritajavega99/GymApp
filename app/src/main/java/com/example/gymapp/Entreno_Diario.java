package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gymapp.Database.GymDB;
import com.example.gymapp.Model.Ejercicio;
import com.example.gymapp.Utilidades.TimeLimit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Entreno_Diario extends AppCompatActivity {

    Button btnStart;
    ImageView ej_image;
    TextView txtGetReady, txtCountdown, txtTimer, ej_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ej_id = 0;
    int limite_tiempo = 0;

    List<Ejercicio> list = new ArrayList<>();

    GymDB gymDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entreno__diario);

        initData();

        gymDB = new GymDB(this);

        btnStart = (Button)findViewById(R.id.btnStart);

        ej_image = (ImageView) findViewById(R.id.detalle_imagen);

        txtCountdown = (TextView) findViewById(R.id.txtCountDown);
        txtGetReady = (TextView) findViewById(R.id.txtGetReady);
        txtTimer = (TextView) findViewById(R.id.temporizador);
        ej_name = (TextView) findViewById(R.id.titulo);

        layoutGetReady = (LinearLayout) findViewById(R.id.layout_get_ready);

        progressBar = (MaterialProgressBar) findViewById(R.id.progressBar);

        progressBar.setMax(list.size());

        setExerciseInformation(ej_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStart.getText().toString().toLowerCase().equals("empezar")){
                    showGetReady();
                    btnStart.setText("hecho");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("hecho")){

                    if(gymDB.getSettingMode() == 0)
                        exercisesFacilModeCountDown.cancel();
                    else if(gymDB.getSettingMode() == 1)
                        exercisesMedioModeCountDown.cancel();
                    else if(gymDB.getSettingMode() == 2)
                        exercisesDificilModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ej_id<list.size()){
                        showRestTime();
                        ej_id++;
                        progressBar.setProgress(ej_id);
                        txtTimer.setText("");
                    }
                    else{
                        showFinished();
                    }

                }
                else{
                    if(gymDB.getSettingMode() == 0)
                        exercisesFacilModeCountDown.cancel();
                    else if(gymDB.getSettingMode() == 1)
                        exercisesMedioModeCountDown.cancel();
                    else if(gymDB.getSettingMode() == 2)
                        exercisesDificilModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ej_id < list.size())
                        setExerciseInformation(ej_id);
                    else
                        showFinished();
                }

            }
        });


    }

    private void showRestTime() {
        ej_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("Saltar");
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountDown.start();

        txtGetReady.setText("Tiempo descanso");

    }

    private void showGetReady() {
        ej_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("¡PREPÁRATE!");
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long l) {
                txtCountdown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();

            }
        }.start();
    }

    private void showExercises() {
        if(ej_id < list.size()){
            ej_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if(gymDB.getSettingMode() == 0)
                exercisesFacilModeCountDown.start();
            else if(gymDB.getSettingMode() == 1)
                exercisesMedioModeCountDown.start();
            else if(gymDB.getSettingMode() == 2)
                exercisesDificilModeCountDown.start();

            //Ponemos datos
            ej_image.setImageResource(list.get(ej_id).getImagen_id());
            ej_name.setText(list.get(ej_id).getNombre());
        }
        else
            showFinished();

    }

    private void showFinished() {
        ej_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("¡SE ACABÓ!");
        txtCountdown.setText("¡Enhorabuena! \nHas acabado con los ejercicios de hoy.");
        txtCountdown.setTextSize(20);

        //Guardarmos el ejercicio realizado en la bbdd
        gymDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
    }



    //CUENTA ATRÁS
    CountDownTimer exercisesFacilModeCountDown = new CountDownTimer(TimeLimit.TIME_LIMIT_FACIL, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ej_id < list.size()-1){
                ej_id++;
                progressBar.setProgress(ej_id);
                txtTimer.setText("");

                setExerciseInformation(ej_id);
                btnStart.setText("Empezar");
            }
            else{
                showFinished();
            }
        }
    };
    CountDownTimer exercisesMedioModeCountDown = new CountDownTimer(TimeLimit.TIME_LIMIT_MEDIO, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ej_id < list.size()-1){
                ej_id++;
                progressBar.setProgress(ej_id);
                txtTimer.setText("");

                setExerciseInformation(ej_id);
                btnStart.setText("Empezar");
            }
            else{
                showFinished();
            }
        }
    };
    CountDownTimer exercisesDificilModeCountDown = new CountDownTimer(TimeLimit.TIME_LIMIT_DIFICIL, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            if(ej_id < list.size()-1){
                ej_id++;
                progressBar.setProgress(ej_id);
                txtTimer.setText("");

                setExerciseInformation(ej_id);
                btnStart.setText("Empezar");
            }
            else{
                showFinished();
            }
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountdown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            setExerciseInformation(ej_id);
            showExercises();

        }
    };

    private void setExerciseInformation(int id) {
        ej_image.setImageResource(list.get(id).getImagen_id());
        ej_name.setText(list.get(id).getNombre());
        btnStart.setText("Empezar");

        ej_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);


    }

    private void initData(){
        list.add(new Ejercicio(R.drawable.abs_uno, "V - SIT"));
        list.add(new Ejercicio(R.drawable.abs_dos, "Bicicleta"));
        list.add(new Ejercicio(R.drawable.brazo_dos, "Flexiones"));
        list.add(new Ejercicio(R.drawable.brazo_seis, "Plancha con mancuerna"));
        list.add(new Ejercicio(R.drawable.pierna_seis, "Elevación pelvis"));
        list.add(new Ejercicio(R.drawable.pierna_cuatro, "Sentadilla sumo con mancuerna"));
        list.add(new Ejercicio(R.drawable.strech_ocho, "Estiramiento"));
        list.add(new Ejercicio(R.drawable.strech_seis, "Estiramiento"));


    }
}