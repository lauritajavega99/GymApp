package com.example.gymapp.Model;

public class Ejercicio {
    private int imagen_id;
    private String nombre;

    public Ejercicio(int imagen_id, String nombre) {
        this.imagen_id = imagen_id;
        this.nombre = nombre;
    }

    public int getImagen_id() {
        return imagen_id;
    }

    public void setImagen_id(int imagen_id) {
        this.imagen_id = imagen_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
