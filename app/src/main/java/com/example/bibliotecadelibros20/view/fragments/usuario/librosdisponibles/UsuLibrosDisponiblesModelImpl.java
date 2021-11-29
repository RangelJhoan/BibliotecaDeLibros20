package com.example.bibliotecadelibros20.view.fragments.usuario.librosdisponibles;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;

import java.util.ArrayList;

public class UsuLibrosDisponiblesModelImpl implements UsuLibrosDisponiblesMVP.Model{
    private ConexionSQLiteHelper conn;
    private UsuLibrosDisponiblesMVP.Presenter presenter;

    public UsuLibrosDisponiblesModelImpl(UsuLibrosDisponiblesMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void consultarLibros(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        presenter.mostrarLibros(conn.consultarLibro());
    }

}
