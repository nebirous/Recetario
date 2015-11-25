package com.example.nebirous.recetario.BD;

import android.provider.BaseColumns;

/**
 * Created by Nebirous on 18/11/2015.
 */
public class Contrato {

    private Contrato (){
    }

    public static abstract class TablaRecetas implements BaseColumns {
        public static final String TABLA = "recetas";
        //public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String INSTRUCCIONES = "instrucciones";
        public static final String FOTO= "foto";
    }

    public static abstract class TablaIngrediente implements BaseColumns {
        public static final String TABLA = "ingredientes";
        //public static final String ID = "id_ingrediente";
        public static final String NOMBRE = "nombre";
    }

    public static abstract class TablaRecetaIngrediente implements BaseColumns {
        public static final String TABLA = "receta_ingrediente";
        //public static final String ID = "id_ri";
        public static final String IDRE = "id_receta";
        public static final String IDING = "id_ingrediente";
        public static final String CANTIDAD = "cantidad";
    }

}
