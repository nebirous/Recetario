package com.example.nebirous.recetario;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nebirous.recetario.Clases.Gestores.GestorIngrediente;
import com.example.nebirous.recetario.Clases.Gestores.GestorReceta;
import com.example.nebirous.recetario.Clases.Gestores.GestorRecetaIngrediente;
import com.example.nebirous.recetario.Clases.Ingrediente;
import com.example.nebirous.recetario.Clases.Receta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nebir on 24/11/2015.
 */
public class Individual extends AppCompatActivity {
    private Intent i;
    private Bundle b;
    private int pos, id;
    private String nombre;
    private android.widget.TextView textView2;
    private android.widget.ImageView imageView4;
    private android.widget.TextView textView3;
    private android.widget.ScrollView scrollView2;
    private GestorRecetaIngrediente gestorRecetaIngredientes;
    private GridLayout lin;
    private List<EditText> ListEditText;
    private List<EditText> ListCantidad=new ArrayList<>();
    private GestorReceta gestorReceta;
    private GestorIngrediente gestorIngrediente;
    private List<Ingrediente> arrayIngrediente;
    private List<Receta> arrayReceta;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual);

        i=getIntent();
        b = i.getExtras();

        pos = b.getInt("position");
        nombre = b.getString("nombre");
        id = b.getInt("id");


        init();
    }
    private void init(){
        this.textView3 = (TextView) findViewById(R.id.textView3);
        this.imageView4 = (ImageView) findViewById(R.id.imageView4);
        this.textView2 = (TextView) findViewById(R.id.textView2);
        gestorReceta = new GestorReceta(this);
        gestorIngrediente=new GestorIngrediente(this);
        gestorRecetaIngredientes=new GestorRecetaIngrediente(this);

        String condicion = "nombre='" + nombre + "' AND _id='"+id+"'";
        arrayReceta = gestorReceta.select(condicion, null);
        for(Receta r: arrayReceta){
            textView2.setText(r.getNombre());
            textView3.setText(r.getInstrucciones());

            File imgFile = new  File(r.getFoto());
            if(imgFile.exists()){
                Bitmap myBitmap =
                        BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageView4.setImageBitmap(myBitmap);
            }

        }
    }

}
