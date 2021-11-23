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

        Cursor cursor = db.rawQuery("SELECT id FROM autor WHERE nombre = ?", new String[]{String.valueOf(libro.getAutor().getNombre())});

        if (cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
            values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
            values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
            values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
            values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
            values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, cursor.getInt(0));

            long res = db.insert(UtilidadesDB.LIBRO_TABLA, UtilidadesDB.LIBRO_ID, values);

            if (res > 0) {
                adminPresenter.mostrarResultado("Se agregó correctamente el libro");
            } else {
                adminPresenter.mostrarResultado("No se agregó el libro");
            }
        } else {
            ContentValues valuesAutor = new ContentValues();
            valuesAutor.put(UtilidadesDB.AUTOR_NOMBRE, libro.getAutor().getNombre());

            long resAutor = db.insert(UtilidadesDB.AUTOR_TABLA, UtilidadesDB.AUTOR_ID, valuesAutor);

            if (resAutor > 0) {
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
                values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
                values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
                values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
                values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
                values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, resAutor);

                long res = db.insert(UtilidadesDB.LIBRO_TABLA, UtilidadesDB.LIBRO_ID, values);

                if (res > 0) {
                    adminPresenter.mostrarResultado("Se agregó correctamente el libro");
                } else {
                    adminPresenter.mostrarResultado("No se agregó el libro");
                }
            } else {
                adminPresenter.mostrarResultado("No se agregó el libro");
            }
        }
        db.close();
    }

    @Override
    public void actualizarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT id FROM autor WHERE nombre = ?", new String[]{String.valueOf(libro.getAutor().getNombre())});
        String[] where = {String.valueOf(libro.getId())};

        if (cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
            values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
            values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
            values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
            values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
            values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, cursor.getInt(0));

            long respuesta = db.update(UtilidadesDB.LIBRO_TABLA, values, UtilidadesDB.LIBRO_ID + " = ?", where);
            if (respuesta > 0) {
                adminPresenter.mostrarResultado("Se editó el libro correctamente");
            } else {
                adminPresenter.mostrarResultado("No se pudo editar el libro");
            }
            db.close();
        } else {
            ContentValues valuesAutor = new ContentValues();
            valuesAutor.put(UtilidadesDB.AUTOR_NOMBRE, libro.getAutor().getNombre());

            long resAutor = db.insert(UtilidadesDB.AUTOR_TABLA, UtilidadesDB.AUTOR_ID, valuesAutor);

            if (resAutor > 0) {
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.LIBRO_TITULO, libro.getTitulo());
                values.put(UtilidadesDB.LIBRO_DESCRIPCION, libro.getDescripcion());
                values.put(UtilidadesDB.LIBRO_URL, libro.getUrl());
                values.put(UtilidadesDB.LIBRO_CANTIDAD, libro.getCantidad());
                values.put(UtilidadesDB.LIBRO_IMAGEN, libro.getImagen());
                values.put(UtilidadesDB.LIBRO_FORANEA_AUTOR, resAutor);
                long respuesta = db.update(UtilidadesDB.LIBRO_TABLA, values, UtilidadesDB.LIBRO_ID + " = ?", where);
                if (respuesta > 0) {
                    adminPresenter.mostrarResultado("Se editó el libro correctamente");
                } else {
                    adminPresenter.mostrarResultado("No se pudo editar el libro");
                }
                db.close();
            } else {
                adminPresenter.mostrarResultado("No se pudo editar el libro");
            }
        }
    }

    @Override
    public void consultarLibros(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<Libro> listaLibros = new ArrayList<>();
        Libro libro = null;
        Autor autor = null;
        Cursor cursor = db.rawQuery("SELECT l.id, l.titulo,l.imagen, l.descripcion, l.cantidad, l.url, a.id, a.nombre " +
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
            libro.setUrl(cursor.getString(5));
            autor.setId(cursor.getInt(6));
            autor.setNombre(cursor.getString(7));
            libro.setAutor(autor);
            listaLibros.add(libro);
        }
        adminPresenter.mostrarLibros(listaLibros);
        cursor.close();
        db.close();
    }

}
