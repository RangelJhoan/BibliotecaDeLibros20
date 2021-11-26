package com.example.bibliotecadelibros20.conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    private static ConexionSQLiteHelper instancia = null;
    private Context mCxt;

    //PARAMETROS CONEXIÓN
    private static final String DATABASE_NAME = "bibliotecadelibros";
    private static final int DATABASE_VERSION = 1;

    //Crear conexión
    public static ConexionSQLiteHelper getInstance(Context ctx) {
        if (instancia == null) {
            instancia = new ConexionSQLiteHelper(ctx.getApplicationContext());
        }
        return instancia;
    }

    private ConexionSQLiteHelper(@Nullable Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCxt = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilidadesDB.CREAR_TABLA_TIPO_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_LIBRO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_PRESTAMO);
        db.execSQL("insert into usuario (nombre) values('Jhoan')");
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_TIPO_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_LIBRO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_PRESTAMO);
        db.execSQL("insert into usuario (nombre) values('Jhoan')");
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
        onCreate(db);
    }

    public long insertarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.CAMPO_NOMBRE, usuario.getNombre());
        values.put(UtilidadesDB.CAMPO_CORREO_ELECTRONICO, usuario.getCorreo_electronico());
        values.put(UtilidadesDB.CAMPO_TELEFONO, usuario.getTelefono());
        values.put(UtilidadesDB.CAMPO_DIRECCION, usuario.getDireccion());
        values.put(UtilidadesDB.CAMPO_CLAVE, usuario.getClave());
        values.put(UtilidadesDB.FORANEA_ID_TIPO_USUARIO, 1);

        long resultado = db.insert(UtilidadesDB.TABLA_USUARIO, UtilidadesDB.CAMPO_ID_USUARIO, values);

        db.close();
        return resultado;
    }

}
