package com.example.nebirous.recetario;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.nebirous.recetario.Clases.Gestores.GestorIngrediente;
import com.example.nebirous.recetario.Clases.Gestores.GestorReceta;
import com.example.nebirous.recetario.Clases.Gestores.GestorRecetaIngrediente;
import com.example.nebirous.recetario.Clases.Ingrediente;
import com.example.nebirous.recetario.Clases.Receta;
import com.example.nebirous.recetario.Clases.RecetaIngrediente;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Altas extends AppCompatActivity {

    private android.widget.EditText etNombre;
    private android.widget.EditText etInstrucciones;
    private android.widget.Button button3;
    private android.widget.ImageView imageView3;
    private android.widget.Button button4;
    private android.widget.ScrollView scrollView;
    private android.widget.Button button5;
    private String rutaFoto = "fail";
    private GestorRecetaIngrediente gestorRecetaIngredientes;
    private GridLayout lin;
    private List<EditText> ListEditText;
    private List<EditText> ListCantidad=new ArrayList<>();
    private GestorReceta gestorReceta;
    private GestorIngrediente gestorIngrediente;
    private List<Ingrediente> arrayIngrediente;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta);
        init();
    }
    private void init(){
        this.button5 = (Button) findViewById(R.id.button5);
        this.scrollView = (ScrollView) findViewById(R.id.scrollView);
        this.button4 = (Button) findViewById(R.id.button4);
        this.imageView3 = (ImageView) findViewById(R.id.imageView3);
        this.button3 = (Button) findViewById(R.id.button3);
        this.etInstrucciones = (EditText) findViewById(R.id.etInstrucciones);
        this.etNombre = (EditText) findViewById(R.id.etNombre);
        this.lin = (GridLayout) findViewById(R.id.lin);
        ListEditText=new ArrayList<>();
        gestorReceta = new GestorReceta(this);
        gestorIngrediente=new GestorIngrediente(this);
        gestorRecetaIngredientes=new GestorRecetaIngrediente(this);

    }
    public void tostada(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gestorIngrediente.open();
        gestorRecetaIngredientes.open();
    }

    @Override
    protected void onPause() {
        gestorIngrediente.close();
        gestorRecetaIngredientes.close();
        super.onPause();
    }

    /*Método que sirve para añadir edittext para los ingredientes*/
    public void anadir(View v){
        EditText ingrediente=new EditText(this);
        EditText cantidad= new EditText(this);
        cantidad.setInputType(2);
        ingrediente.setHint(R.string.ingredientes);
        cantidad.setHint(R.string.cantidad);
        ListEditText.add(ingrediente);
        ListCantidad.add(cantidad);
        ViewGroup.LayoutParams parametros =new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lin.addView(ingrediente, parametros);
        lin.addView(cantidad, parametros);

    }

    public void imagen(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    rutaFoto=filePath;

                    File imgFile = new  File(filePath);
                    if(imgFile.exists()){
                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imageView3.setImageBitmap(myBitmap);

                    }
                }
        }
    }


    public void guardar(View v){
        String nombre = etNombre.getText().toString();
        String instrucciones = etInstrucciones.getText().toString();
        if(nombre.isEmpty() || instrucciones.isEmpty()){
            tostada("No puedes dejar el nombre o las instrucciones vacías");
        }else {
            Receta receta = new Receta();
            receta.setNombre(nombre);
            receta.setInstrucciones(instrucciones);
            receta.setFoto(rutaFoto);
            RecetaIngrediente recetaIngredientes;
            Ingrediente aux;
            int idReceta = (int) gestorReceta.insert(receta);
            int idIngrediente;
            int cantidad = -1;
            String nombreIng;
            for (int i = 0; i < ListEditText.size(); i++) {
                nombreIng= ListEditText.get(i).getText().toString();
                if(nombreIng.isEmpty()) {
                    tostada("No dejes el nombre del ingrediente en blanco");
                }else{
                    String condicion = "nombre='" + nombreIng + "'";
                    System.out.println("*************"+condicion);

                    System.out.println(gestorIngrediente.select(condicion, null).toString());
                    arrayIngrediente = gestorIngrediente.select(condicion, null);
                    if (arrayIngrediente.size() > 0) {
                        idIngrediente = arrayIngrediente.get(0).getId();
                        String cant= ListCantidad.get(i).getText().toString();
                        if(cant.isEmpty())
                            tostada("No dejes la cantidad enblanco");
                        else{
                            cantidad = Integer.parseInt(cant);
                            recetaIngredientes = new RecetaIngrediente(idReceta, idIngrediente, cantidad);//PONER CANTIDAD
                            gestorRecetaIngredientes.insert(recetaIngredientes);
                            setResult(Activity.RESULT_OK);
                        }
                    } else if (arrayIngrediente.size() == 0) {

                        aux = new Ingrediente();
                        nombreIng= ListEditText.get(i).getText().toString();
                        if(nombreIng.isEmpty()){
                            tostada("No dejes el nombre de ingrediente vacio");
                        }else{
                            aux.setNombre(nombreIng);
                            idIngrediente = (int) gestorIngrediente.insert(aux);
                            String cadCant= ListCantidad.get(i).getText().toString();
                            if(cadCant.isEmpty()){
                                tostada("No dejes la cantidad vacia");
                            }else{
                                cantidad = Integer.parseInt(cadCant);
                                recetaIngredientes = new RecetaIngrediente(idReceta, idIngrediente, cantidad);
                                gestorRecetaIngredientes.insert(recetaIngredientes);
                                setResult(Activity.RESULT_OK);
                            }
                        }
                    }
                }
            }
            finish();
        }

    }
}
