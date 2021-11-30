package com.example.bibliotecadelibros20.view.fragments.administrador.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;

public class LibrosPrestadosModelImpl implements LibrosPrestadosMVP.Model{

    private ConexionSQLiteHelper conn;
    private LibrosPrestadosMVP.Presenter presenter;

    public LibrosPrestadosModelImpl(LibrosPrestadosMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void consultarPrestamo(Context context) {
        conn = ConexionSQLiteHelper.getInstance(context);
        presenter.mostrarPrestamo(conn.consultarLibrosPrestados());
    }
}
