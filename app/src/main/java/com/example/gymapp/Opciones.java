package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gymapp.Database.GymDB;

import java.util.Calendar;
import java.util.Date;

public class Opciones extends AppCompatActivity {

    Button btnGuardar;
    RadioButton rdiFacil;
    RadioButton rdiMedio;
    RadioButton rdiDificil;
    RadioGroup rdiGroup;
    GymDB gymDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        //Iniciamos vista

        btnGuardar = (Button)findViewById(R.id.btnGuardar);

        rdiGroup = (RadioGroup) findViewById(R.id.rdiGroup);
        rdiFacil = (RadioButton)findViewById(R.id.rdiFacil);
        rdiMedio = (RadioButton)findViewById(R.id.rdiMedio);
        rdiDificil = (RadioButton)findViewById(R.id.rdiDificil);

        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        gymDB = new GymDB(this);

        int modo = gymDB.getSettingMode();
        setRadioButton(modo);

        //evento
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarModo();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(Opciones.this, "¡Configuración guardada!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked) {
        if(checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(Opciones.this,NotificacionAlarma.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            //Poner el tiempo de alarma
            Calendar calendar = Calendar.getInstance();
            Date hoy = Calendar.getInstance().getTime();
            calendar.set(hoy.getYear(),hoy.getMonth(),hoy.getDay(),timePicker.getHour(),timePicker.getMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Log.d("DEBUG", "La alarma sonará a las "+timePicker.getHour()+":"+timePicker.getMinute());
        }
        else{
            //Cancelamos alarma
            Intent intent = new Intent(Opciones.this,NotificacionAlarma.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);

        }
    }
    //Guadar el modo de temporizador
    private void guardarModo(){
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if(selectedID == rdiFacil.getId())
            gymDB.saveSettingMode(0);
        else if(selectedID == rdiMedio.getId())
            gymDB.saveSettingMode(1);
        else if(selectedID == rdiDificil.getId())
            gymDB.saveSettingMode(2);

    }

    private void setRadioButton(int modo){
        if (modo == 0)
            rdiGroup.check(R.id.rdiFacil);
        else if (modo == 1)
            rdiGroup.check(R.id.rdiMedio);
        else if (modo == 2)
            rdiGroup.check(R.id.rdiDificil);

    }
}