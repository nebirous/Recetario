package com.example.nebirous.recetario.Clases;

import android.database.Cursor;

import com.example.nebirous.recetario.BD.Contrato;

/**
 * Created by nebir on 21/11/2015.
 */
public class Ingrediente {
    private int id;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void set(Cursor c){
        setId(c.getInt(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBRE)));

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
