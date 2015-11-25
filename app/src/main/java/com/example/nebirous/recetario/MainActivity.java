package com.example.nebirous.recetario;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nebirous.recetario.Adaptador.Adaptador;
import com.example.nebirous.recetario.Clases.Gestores.GestorReceta;
import com.example.nebirous.recetario.Clases.Receta;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GestorReceta gp;
    private Adaptador adr;
    private ListView lv;
    private Cursor c;
    private List<Receta> recetas = new ArrayList<>();
    private final static int ALTA=1, EDITAR=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetario);
        gp = new GestorReceta(this);
        lv = (ListView) findViewById(R.id.lv);
        init();
    }
    /*@Override
    protected void onResume(){
        gp.open();
        c = gp.getCursor();
        adr = new Adaptador(this, R.layout.item, recetas);
        lv.setAdapter(adr);
        super.onResume();
    }*/
    @Override
    protected void onResume(){
        super.onResume();
        generarAdaptador();
    }

//    @Override
//    protected void onDestroy(){
//        gp.close();
//        super.onDestroy();
//    }

    public void insertar(View v){
        Intent i = new Intent(this, Altas.class);
        startActivityForResult(i, ALTA);
    }

    private void init(){
        generarAdaptador();
        final Context ctx = this;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ctx, Individual.class);
                Bundle b = new Bundle();
                b.putInt("Posicion", position);
                b.putString("nombre", recetas.get(position).getNombre());
                b.putInt("id", recetas.get(position).getId());
                i.putExtras(b);
                startActivity(i);
            }
        });



        registerForContextMenu(lv);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recetas, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        long id = item.getItemId();
        AdapterView.AdapterContextMenuInfo vistaInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = vistaInfo.position;

        if (id == R.id.action_editar) {
            editar(posicion);
            return true;
        } else if (id == R.id.action_borrar) {
            borrar(posicion);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void generarAdaptador(){
        recetas = gp.select();
        adr = new Adaptador(this, R.layout.item,recetas);
        lv.setAdapter(adr);

    }

    public void borrar(int posicion){
        gp.delete(posicion);
        generarAdaptador();
    }
    public void editar(int posicion){
        Intent i = new Intent(this, Editar.class);
        Bundle b = new Bundle();
        b.putInt("Posicion", posicion);
        b.putString("nombre", recetas.get(posicion).getNombre());
        b.putInt("id", recetas.get(posicion).getId());
        i.putExtras(b);
        startActivityForResult(i,EDITAR);
    }

}
