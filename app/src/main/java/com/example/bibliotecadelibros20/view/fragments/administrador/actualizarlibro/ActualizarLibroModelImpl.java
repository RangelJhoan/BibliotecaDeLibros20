package com.example.bibliotecadelibros20.view.fragments.administrador.actualizarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;

public class ActualizarLibroModelImpl implements ActualizarLibroMVP.Model{

    ActualizarLibroMVP.Presenter presenter;
    ConexionSQLiteHelper conn;

    public ActualizarLibroModelImpl(ActualizarLibroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void actualizarLibro(Context context, Libro libro) {
        conn = ConexionSQLiteHelper.getInstance(context);
        long respuesta = conn.actualizarLibro(libro);
        if (respuesta > 0) {
            presenter.mostrarResultado("Se editó el libro correctamente");
        } else {
            presenter.mostrarResultado("No se pudo editar el libro");
        }
    }
}
