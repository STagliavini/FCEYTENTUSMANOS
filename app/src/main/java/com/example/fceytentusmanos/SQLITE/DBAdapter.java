package com.example.fceytentusmanos.SQLITE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String CLAVE_ID = "_id";
    static final String CLAVE_NOMBRE ="nombre";
    static final String BASE_DE_DATOS = "Autoridades";
    static final String TABLA = "autoridad";
    static final int VERSION = 1;
    static final String CREAR_DB =
            "CREATE TABLE pais (_id INTEGER PRIMARY KEY,"+
            "nombre TEXT);";

    final Context contexto;
    DataBaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context contexto){
        this.contexto = contexto;
        DBHelper = new DataBaseHelper(contexto);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context contexto){
            super(contexto, BASE_DE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(CREAR_DB);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,int versionAnterior, int nuevaVersion){
            Log.w("DBAdapter", "Actualizando desde la version "+versionAnterior+
                    " a la version "+nuevaVersion+". Se eliminaran todos los datos");
            db.execSQL("DROP TABLE IF EXISTS autoridad;");
            onCreate(db);
            db.execSQL("ALTER TABLE autoridad ADD COLUMN bandera BLOB;");
        }

    }

    // Abre la Base de Datos
    public DBAdapter abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;//db?
    }
    // Cierra la Base de Datos
    public void cerrar(){
        DBHelper.close();
    }
    // Inserta un pais en la Base de Datos
    public long insertarAutoridad(String nombre){
        ContentValues valores = new ContentValues();
        valores.put(CLAVE_NOMBRE, nombre);
        return db.insert(TABLA, null, valores);
    }
    // Elimina un pais de la Base de Datos
    public boolean eliminarAutoridad(long id){
        return db.delete(TABLA, CLAVE_ID + " = "+id,null) > 0;
    }
    // Recupera todos los paises de la Base de Datos
    public Cursor getAutoridades(){
        return db.query(TABLA, new String[] {CLAVE_ID, CLAVE_NOMBRE},
                null, null, null, null, null);
    }
    // Recupera un pais de la Base de Datos
    public Cursor getAutoridades(long id){
        Cursor cursor =
                db.query(true, TABLA, new String[] {CLAVE_ID, CLAVE_NOMBRE},
                CLAVE_ID+"=" + id, null, null, null, null, null);
        if (cursor !=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    // Actualiza un pais de la Base de Datos
    public boolean actualizarAutoridad(long id, String nombre){
        ContentValues valores = new ContentValues();
        valores.put(CLAVE_NOMBRE, nombre);
        return db.update(TABLA, valores, CLAVE_ID+"="+id, null)>0;
    }
}
