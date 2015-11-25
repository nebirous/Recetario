package com.example.nebirous.recetario.Clases.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nebirous.recetario.BD.Ayudante;
import com.example.nebirous.recetario.BD.Contrato;
import com.example.nebirous.recetario.Clases.Ingrediente;
import com.example.nebirous.recetario.Clases.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nebir on 21/11/2015.
 */
public class GestorIngrediente {
    private Ayudante adb;
    private SQLiteDatabase db;

    public GestorIngrediente (Context c){
        adb = new Ayudante(c);
    }

    public void open(){
        db = adb.getWritableDatabase();
    }
    public void openRead(){
        db = adb.getReadableDatabase();
    }
    public void close(){
        adb.close();
    }
    public int insert(Ingrediente r){
        ContentValues v = new ContentValues();
        v.put(Contrato.TablaIngrediente.NOMBRE, r.getNombre());
        int id = (int) db.insert(Contrato.TablaIngrediente.TABLA, null, v);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { id +" "};
        int cuenta = db.delete(Contrato.TablaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Ingrediente r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaIngrediente.NOMBRE, r.getNombre());


        String condicion = Contrato.TablaIngrediente._ID + " = ?";
        String[] argumentos = { r.getId() +" "};

        int cuenta = db.update(Contrato.TablaIngrediente.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public Ingrediente getRow(Cursor c){
        Ingrediente r = new Ingrediente();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaIngrediente._ID)));
        r.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngrediente.NOMBRE)));
        return r;
    }
    public Ingrediente getRow(int id){
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }
    public Cursor getCursor(){return getCursor(null, null);}
    public Cursor getCursor(String condicion, String[] parametros){
        Cursor cursor = db.query(
                Contrato.TablaIngrediente.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaIngrediente._ID + ", " +Contrato.TablaIngrediente.NOMBRE);
        return cursor;
    }
    public List<Ingrediente> select(String condicion, String[] parametros){
        List<Ingrediente> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Ingrediente r;
        while (cursor.moveToNext()){
            r = getRow(cursor);
            la.add(r);
        }
        cursor.close();
        return la;
    }
    public List<Ingrediente> select(){ return select(null, null);}
}
