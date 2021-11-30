package com.example.bibliotecadelibros20.view.fragments.administrador.agregarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;

public class AgregarLibroModelImpl implements AgregarLibroMVP.Model{

    ConexionSQLiteHelper conn;
    AgregarLibroMVP.Presenter presenter;

    public AgregarLibroModelImpl(AgregarLibroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void agregarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        long res = conn.agregarLibro(libro);
        if (res > 0) {
            presenter.mostrarResultado("Se agregó correctamente el libro");
        } else {
            presenter.mostrarResultado("No se agregó el libro");
        }
    }
}
