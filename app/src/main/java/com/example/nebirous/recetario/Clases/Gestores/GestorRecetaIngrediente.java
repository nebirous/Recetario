package com.example.nebirous.recetario.Clases.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nebirous.recetario.BD.Ayudante;
import com.example.nebirous.recetario.BD.Contrato;
import com.example.nebirous.recetario.Clases.Receta;
import com.example.nebirous.recetario.Clases.RecetaIngrediente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nebir on 21/11/2015.
 */
public class GestorRecetaIngrediente {
    private Ayudante adb;
    private SQLiteDatabase db;

    public GestorRecetaIngrediente (Context c){
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
    public int insert(RecetaIngrediente r){
        ContentValues v = new ContentValues();
        v.put(Contrato.TablaRecetaIngrediente.IDRE, r.getIdReceta());
        v.put(Contrato.TablaRecetaIngrediente.IDING, r.getIdIngrediente());
        v.put(Contrato.TablaRecetaIngrediente.CANTIDAD,r.getCantidad());
        int id = (int) db.insert(Contrato.TablaRecetas.TABLA, null, v);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaRecetaIngrediente._ID + " = ?";
        String[] argumentos = { id +" "};
        int cuenta = db.delete(Contrato.TablaRecetaIngrediente.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(RecetaIngrediente r){
        ContentValues v = new ContentValues();
        v.put(Contrato.TablaRecetaIngrediente.IDRE, r.getIdReceta());
        v.put(Contrato.TablaRecetaIngrediente.IDING, r.getIdIngrediente());
        v.put(Contrato.TablaRecetaIngrediente.CANTIDAD,r.getCantidad());

        String condicion = Contrato.TablaRecetaIngrediente._ID + " = ?";
        String[] argumentos = { r.getId() +" "};

        int cuenta = db.update(Contrato.TablaRecetaIngrediente.TABLA, v, condicion, argumentos);
        return cuenta;
    }

    public RecetaIngrediente getRow(Cursor c){
        RecetaIngrediente r = new RecetaIngrediente();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente._ID)));
        r.setIdReceta(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDRE)));
        r.setIdIngrediente(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.IDING)));
        r.setCantidad(c.getInt(c.getColumnIndex(Contrato.TablaRecetaIngrediente.CANTIDAD)));
        return r;
    }
    public RecetaIngrediente getRow(int id){
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }
    public Cursor getCursor(){return getCursor(null, null);}
    public Cursor getCursor(String condicion, String[] parametros){
        Cursor cursor = db.query(
                Contrato.TablaRecetaIngrediente.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaRecetaIngrediente._ID
        );
        return cursor;
    }
    public List<RecetaIngrediente> select(String condicion, String[] parametros){
        List<RecetaIngrediente> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        RecetaIngrediente r;
        while (cursor.moveToNext()){
            r = getRow(cursor);
            la.add(r);
        }
        cursor.close();
        return la;
    }
    public List<RecetaIngrediente> select(){ return select(null, null);}
}
