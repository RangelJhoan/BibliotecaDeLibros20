package com.example.bibliotecadelibros20.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public class Validaciones {
    static ConexionSQLiteHelper conn;

    public static boolean validarCampos(EditText[] listaTextViews){
        for (int i=0;i<listaTextViews.length;i++){
            if(listaTextViews[i].getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

    //Valida que hayan libros disponibles para prestar
    public static boolean validarPrestamo(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(id) FROM prestamo WHERE id_libro = ?", new String[] {String.valueOf(libro.getId())});
        if(cursor.moveToFirst()){
            int cantidadLibrosPrestados = cursor.getInt(0);
            if((libro.getCantidad() - cantidadLibrosPrestados) > 0){
                return true;
            }else {
                db.close();
                cursor.close();
                return false;
            }
        }
        return false;
    }

    //Verifica que el usuario no haya prestado el libro antes
    public static boolean verificarPrestamo(Context context, int id_usuario, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT p.id " +
                        "FROM prestamo p " +
                        "JOIN usuario u ON u.id = p.id_usuario " +
                        "JOIN libro l ON l.id = p.id_libro " +
                        "WHERE u.id = ? and l.id = ?",
                new String[]{String.valueOf(id_usuario), String.valueOf(libro.getId())});
        //String retrun = cursor.getString(0);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return false;
        } else {
            cursor.close();
            db.close();
            return true;
        }
    }

}
