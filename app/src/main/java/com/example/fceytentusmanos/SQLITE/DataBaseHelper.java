package com.example.fceytentusmanos.SQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.HashMap;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String BASE_DE_DATOS = "Autoridades";
    private static final int VERSION = 1;

    public DataBaseHelper(Context contexto){
        super(contexto, BASE_DE_DATOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE autoridad (_id INTEGER PRIMARY KEY,"+
                "nombre TEXT);");
        insertIni("Departamento Académico de Agrimensura",db);
        insertIni("Departamento Académico de Educación y Formación Complementaria",db);
        insertIni("Departamento Académico de Dibujo",db);
        insertIni("Departamento Académico de Electricidad",db);
        insertIni("Departamento Académico de Electrónica",db);
    }
    public void insertIni(String nombre,SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put("nombre", nombre);
        long resultadoInsert = db.insert("autoridad", null, valores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int versionAnterior, int nuevaVersion){
        db.execSQL("ALTER TABLE autoridad ADD COLUMN bandera BLOB);");
    }
}
