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
        long res = conn.agregarLibro(libro);
        if (res > 0) {
            adminPresenter.mostrarResultado("Se agregó correctamente el libro");
        } else {
            adminPresenter.mostrarResultado("No se agregó el libro");
        }
    }

    @Override
    public void actualizarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        long respuesta = conn.actualizarLibro(libro);
        if (respuesta > 0) {
            adminPresenter.mostrarResultado("Se editó el libro correctamente");
        } else {
            adminPresenter.mostrarResultado("No se pudo editar el libro");
        }
    }

    @Override
    public void consultarLibros(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        adminPresenter.mostrarLibros(conn.consultarLibros());
    }

    @Override
    public void consultarLibrosPrestados(Context context) {

    }

}
