package com.example.nebirous.recetario.Clases;

import android.database.Cursor;

import com.example.nebirous.recetario.BD.Contrato;

/**
 * Created by nebir on 21/11/2015.
 */
public class RecetaIngrediente {
    private int idIngrediente, idReceta, id, cantidad;

    public RecetaIngrediente() {
    }

    public RecetaIngrediente(int idIngrediente, int idReceta, int cantidad) {
        this.idIngrediente = idIngrediente;
        this.idReceta = idReceta;
        this.cantidad = cantidad;
    }

    public void set(Cursor c){
        setIdIngrediente(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDING)));
        setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente._ID)));
        setIdReceta(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDRE)));
        setCantidad(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD)));
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RecetaIngrediente{" +
                "idIngrediente=" + idIngrediente +
                ", idReceta=" + idReceta +
                ", id=" + id +
                ", cantidad=" + cantidad +
                '}';
    }
}
