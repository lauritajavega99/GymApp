package com.example.gymapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class GymDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "Gym.db";
    private static final int DB_VER = 1;

    public GymDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public int getSettingMode(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Modo"};
        String sqlTable = "Opciones";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect, null, null, null, null, null);
        c.moveToFirst();

        return c.getInt(c.getColumnIndex("Modo"));

    }

    //Función para escribir los datos en la tabla
    public void saveSettingMode (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query =  "UPDATE Opciones SET Modo ="+value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDays(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"Day"};
        String sqlTable = "Entreno";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect, null, null, null, null, null);

        List<String> result = new ArrayList<>();
        if(c.moveToFirst()){

            do{
                result.add(c.getString(c.getColumnIndex("Day")));
            }while (c.moveToNext());
        }
        return result;

    }

    public void saveDay (String value){
        SQLiteDatabase db = getReadableDatabase();
        String query =  String.format("INSERT INTO Entreno(Day) VALUES('%s');",value);
        db.execSQL(query);
    }
}
