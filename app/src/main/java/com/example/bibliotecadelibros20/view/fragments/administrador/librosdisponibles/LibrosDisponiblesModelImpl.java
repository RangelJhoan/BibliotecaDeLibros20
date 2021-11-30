package com.example.bibliotecadelibros20.view.fragments.administrador.librosdisponibles;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;

public class LibrosDisponiblesModelImpl implements LibrosDisponiblesMVP.Model{

    ConexionSQLiteHelper conn;
    LibrosDisponiblesMVP.Presenter presenter;

    public LibrosDisponiblesModelImpl(LibrosDisponiblesMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void consultarLibrosDisponibles(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        presenter.mostrarLibros(conn.consultarLibros());
    }
}
