package com.example.nebirous.recetario.Adaptador;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nebirous.recetario.Clases.Receta;
import com.example.nebirous.recetario.R;

import java.util.List;

/**
 * Created by nebir on 21/11/2015.
 */

public class Adaptador extends ArrayAdapter<Receta> {
    private List<Receta> lista;
    private Context contexto;
    private int res;
    private LayoutInflater i;

    public Adaptador(Context contexto, int res, List<Receta> lista){
        super(contexto, res,lista);
        this.contexto=contexto;
        this.res=res;
        this.lista=lista;
        i = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    static class GuardarLista{
        public TextView tv1;
        public ImageView iv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        GuardarLista gv = new GuardarLista();

        if(convertView==null){
            convertView = i.inflate(res, null);
            TextView tv = (TextView)convertView.findViewById(R.id.textView);
            gv.tv1= tv;
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView2);
            gv.iv= iv;
            convertView.setTag(gv);
        }else gv = (GuardarLista) convertView.getTag();


        gv.tv1.setText("" + lista.get(position).getNombre());
//        gv.iv.setText("" + lista.get(position).getInstrucciones());

        return convertView;
    }
}

/*
public class Adaptador extends CursorAdapter{
    public Adaptador (Context ctx, Cursor cu){ super(ctx, cu, true);}

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1 = (TextView) view.findViewById(R.id.textView);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView2);

        Receta r = new Receta();
        r.set(cursor);
        tv1.setText(r.getNombre());
    }
}
*/
