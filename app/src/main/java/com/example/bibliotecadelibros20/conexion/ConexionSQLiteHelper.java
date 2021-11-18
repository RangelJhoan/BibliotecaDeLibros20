package com.example.bibliotecadelibros20.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    private static ConexionSQLiteHelper instancia = null;

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_TIPO_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_LIBRO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_PRESTAMO);
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
        db.execSQL("insert into autor (nombre) values('Autor 1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_TIPO_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + UtilidadesDB.TABLA_USUARIO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_AUTOR);
        db.execSQL(UtilidadesDB.CREAR_TABLA_LIBRO);
        db.execSQL(UtilidadesDB.CREAR_TABLA_PRESTAMO);
        db.execSQL("insert into tipo_usuario (descripcion) values('usuario')");
        db.execSQL("insert into autor (nombre) values('Autor 1')");
        onCreate(db);
    }
}
