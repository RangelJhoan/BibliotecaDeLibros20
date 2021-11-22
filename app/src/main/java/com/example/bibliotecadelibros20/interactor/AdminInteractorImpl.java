package com.example.bibliotecadelibros20.interactor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.interfaces.AdminInteractor;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;

import java.util.ArrayList;

public class AdminInteractorImpl implements AdminInteractor {
    private ConexionSQLiteHelper conn;
    private AdminPresenter adminPresenter;

    public AdminInteractorImpl(AdminPresenter adminPresenter) {
        this.adminPresenter = adminPresenter;
    }

    @Override
    public void agregarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
        values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
        values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
        values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
        values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
        values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, libro.getAutor().getId());

        long res = db.insert(UtilidadesDB.LIBRO_TABLA, UtilidadesDB.LIBRO_ID, values);

        if (res > 0) {
            adminPresenter.mostrarResultado("Se agregó correctamente el libro");
        } else {
            adminPresenter.mostrarResultado("No se agregó el libro");
        }
        db.close();
    }

    @Override
    public void consultarLibros(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        Libro libro = null;
        Autor autor = null;
        Cursor cursor = db.rawQuery("SELECT l.id, l.titulo,l.imagen, l.descripcion, l.cantidad, a.nombre, l.url " +
                "FROM " + UtilidadesDB.LIBRO_TABLA + " l " +
                "JOIN " + UtilidadesDB.AUTOR_TABLA + " a ON l.id_autor = a.id", null);
        while (cursor.moveToNext()) {
            libro = new Libro();
            autor = new Autor();


            libro.setId(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setImagen(cursor.getString(2));
            libro.setDescripcion(cursor.getString(3));
            libro.setCantidad(cursor.getInt(4));
            autor.setNombre(cursor.getString(5));
            libro.setUrl(cursor.getString(6));
            libro.setAutor(autor);
            listaLibros.add(libro);
        }
        adminPresenter.mostrarLibros(listaLibros);
        cursor.close();
        db.close();
    }
    /*
    @Override
    public void actualizarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
        values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
        values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
        values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
        values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
        values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, libro.getAutor().getId());

        long res = db.update(UtilidadesDB.LIBRO_TABLA, values, "where " + UtilidadesDB.LIBRO_ID + "=?", new String[]{String.valueOf(libro.getId())});

        if (res > 0) {
            adminPresenter.mostrarResultado("Se actualizó correctamente el libro");
        } else {
            adminPresenter.mostrarResultado("No se actualizó el libro");
        }
        db.close();
    }

     */

}
