package com.example.nebirous.recetario.Clases.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nebirous.recetario.BD.Ayudante;
import com.example.nebirous.recetario.BD.Contrato;
import com.example.nebirous.recetario.Clases.Receta;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nebir on 21/11/2015.
 */
public class GestorReceta {
    private Ayudante adb;
    private SQLiteDatabase db;

    public GestorReceta (Context c){
        adb = new Ayudante(c);
        db = adb.getWritableDatabase();
    }

    public void open(){

    }
    public void openRead(){
        db = adb.getReadableDatabase();
    }
    public void close(){
        adb.close();
    }
    public int insert(Receta r){
        ContentValues v = new ContentValues();
        v.put(Contrato.TablaRecetas.NOMBRE, r.getNombre());
        v.put(Contrato.TablaRecetas.INSTRUCCIONES, r.getInstrucciones());
        v.put(Contrato.TablaRecetas.FOTO, r.getFoto());
        int id = (int) db.insert(Contrato.TablaRecetas.TABLA, null, v);
        return id;
    }
    public int delete(int id){
        String condicion = Contrato.TablaRecetas._ID + " = ?";
        String[] argumentos = { id +" "};
        int cuenta = db.delete(Contrato.TablaRecetas.TABLA, condicion, argumentos);
        return cuenta;
    }

    public int update(Receta r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaRecetas.NOMBRE, r.getNombre());
        valores.put(Contrato.TablaRecetas.INSTRUCCIONES, r.getInstrucciones());
        valores.put(Contrato.TablaRecetas.FOTO, r.getFoto());

        String condicion = Contrato.TablaRecetas._ID + " = ?";
        String[] argumentos = { r.getId() +" "};

        int cuenta = db.update(Contrato.TablaRecetas.TABLA, valores, condicion, argumentos);
        return cuenta;
    }

    public Receta getRow(Cursor c){
        Receta r = new Receta();
        r.setId(c.getInt(c.getColumnIndex(Contrato.TablaRecetas._ID)));
        r.setNombre(c.getString(c.getColumnIndex(Contrato.TablaRecetas.NOMBRE)));
        r.setInstrucciones(c.getString(c.getColumnIndex(Contrato.TablaRecetas.INSTRUCCIONES)));
        r.setFoto(c.getString(c.getColumnIndex(Contrato.TablaRecetas.FOTO)));
        return r;
    }
    public Receta getRow(int id){
        Cursor c = getCursor("_id = ?", new String[]{id+""});
        return getRow(c);
    }
    public Cursor getCursor(){return getCursor(null, null);}
    public Cursor getCursor(String condicion, String[] parametros){
        Cursor cursor = db.query(
                Contrato.TablaRecetas.TABLA, null, condicion, parametros, null,
                null, Contrato.TablaRecetas.NOMBRE+", "+Contrato.TablaRecetas.INSTRUCCIONES
        );
        return cursor;
    }
    public List<Receta> select(String condicion, String[] parametros){
        List<Receta> la = new ArrayList<>();
        Cursor cursor = getCursor(condicion, parametros);
        Receta r;
        while (cursor.moveToNext()){
            r = getRow(cursor);
            la.add(r);
        }
        cursor.close();
        return la;
    }
    public List<Receta> select(){ return select(null, null);}

}
