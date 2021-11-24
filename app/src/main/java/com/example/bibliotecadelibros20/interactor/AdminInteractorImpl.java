package com.example.bibliotecadelibros20.interactor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.interfaces.AdminInteractor;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

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

    @Override
    public void consultarLibrosPrestados(Context context) {

    }

    @Override
    public void consultarLibrosPrestadosUsu(Context context, int id_usuario) {
        conn = ConexionSQLiteHelper.getInstance(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        Libro libro = null;
        Prestamo prestamo = null;
        Autor autor = null;
        ArrayList<Prestamo> listaPrestamo = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT l.titulo, l.imagen, a.nombre, p.fecha_prestamo, l.descripcion, l.url, p.id, l.cantidad, l.id " +
                "FROM usuario u " +
                "JOIN prestamo p ON p.id_usuario = u.id " +
                "JOIN libro l ON l.id = p.id_libro " +
                "JOIN autor a ON a.id = l.id_autor " +
                "WHERE u.id = " + id_usuario, null);

        while (cursor.moveToNext()) {
            libro = new Libro();
            prestamo = new Prestamo();
            autor = new Autor();

            autor.setNombre(cursor.getString(2));
            prestamo.setFecha_prestamo(cursor.getString(3));
            prestamo.setId(cursor.getInt(6));
            libro.setTitulo(cursor.getString(0));
            libro.setImagen(cursor.getString(1));
            libro.setDescripcion(cursor.getString(4));
            libro.setUrl(cursor.getString(5));
            libro.setCantidad(cursor.getInt(7));
            libro.setId(cursor.getInt(8));
            libro.setAutor(autor);

            prestamo.setLibro(libro);
            listaPrestamo.add(prestamo);
            adminPresenter.mostrarLibrosPrestados(listaPrestamo);

        }
        cursor.close();
        db.close();
    }

    @Override
    public void prestarLibro(Context context, Libro libro, int id_usuario) {
        conn = ConexionSQLiteHelper.getInstance(context);
        if(Validaciones.validarPrestamo(context,libro)){
            if(Validaciones.verificarPrestamo(context,id_usuario,libro)){
                SQLiteDatabase db = conn.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.PRESTAMO_ID_LIBRO, libro.getId());
                values.put(UtilidadesDB.PRESTAMO_ID_USUARIO, id_usuario);

                long res = db.insert(UtilidadesDB.PRESTAMO_TABLA, UtilidadesDB.PRESTAMO_ID, values);

                if (res > 0) {
                    adminPresenter.mostrarResultado("Se prestó correctamente el libro");
                } else {
                    adminPresenter.mostrarResultado("No se prestó el libro");
                }
            }else{
                adminPresenter.mostrarResultado("Ya haz registrado este libro previamente");
            }
        }else{
            adminPresenter.mostrarResultado("Cantidad de libros no disponible para prestar");
        }
    }

}
