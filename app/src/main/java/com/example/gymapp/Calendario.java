package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gymapp.Custom.WorkoutDoneDecorator;
import com.example.gymapp.Database.GymDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendario extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();

    GymDB gymDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        gymDB = new GymDB(this);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendario);
        List<String> workoutDay = gymDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for(String value:workoutDay)
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}