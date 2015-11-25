package com.example.nebirous.recetario.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nebir on 21/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "filmoteca.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante (Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql="drop table if exists "+ Contrato.TablaRecetas.TABLA;
        db.execSQL(sql);
        sql="drop table if exists "+ Contrato.TablaIngrediente.TABLA;
        db.execSQL(sql);
        sql="drop table if exist "+ Contrato.TablaRecetaIngrediente.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql;
        sql="create table "+Contrato.TablaRecetas.TABLA+
                " ("+
                Contrato.TablaRecetas._ID + " integer primary key autoincrement, "+
                Contrato.TablaRecetas.NOMBRE+" text, "+
                Contrato.TablaRecetas.INSTRUCCIONES +" text," +
                Contrato.TablaRecetas.FOTO+" text "+
                ")";
        db.execSQL(sql);
        sql="create table "+Contrato.TablaIngrediente.TABLA+
                " ("+
                Contrato.TablaIngrediente._ID + " integer primary key autoincrement, "+
                Contrato.TablaIngrediente.NOMBRE+" text "+
                ")";
        db.execSQL(sql);

        sql="create table "+Contrato.TablaRecetaIngrediente.TABLA+
                " ("+
                Contrato.TablaRecetaIngrediente._ID + " integer primary key autoincrement, "+
                Contrato.TablaRecetaIngrediente.IDING+" integer, "+
                Contrato.TablaRecetaIngrediente.IDRE+" integer, "+
                Contrato.TablaRecetaIngrediente.CANTIDAD+" integer" +
                ")";
        db.execSQL(sql);
    }

}
