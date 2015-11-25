package com.example.nebirous.recetario.Clases;

import android.database.Cursor;

import com.example.nebirous.recetario.BD.Contrato;

import java.util.List;

/**
 * Created by nebir on 21/11/2015.
 */
public class Receta {
    private String nombre, foto, instrucciones;
    private int id;

    public Receta() {
    }

    public Receta(String nombre, String foto, String instrucciones) {
        this.nombre = nombre;
        this.foto = foto;
        this.instrucciones = instrucciones;
    }

    public void set(Cursor c){
        setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetas._ID)));
        setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetas.NOMBRE)));
        setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetas.INSTRUCCIONES)));
        setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetas.FOTO)));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", foto='" + foto + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                ", id=" + id +
                '}';
    }
}
