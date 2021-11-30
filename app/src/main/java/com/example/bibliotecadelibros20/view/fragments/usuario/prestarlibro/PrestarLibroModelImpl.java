package com.example.bibliotecadelibros20.view.fragments.usuario.prestarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

public class PrestarLibroModelImpl implements PrestarLibroMVP.Model {
    private ConexionSQLiteHelper conn;
    private PrestarLibroMVP.Presenter presenter;

    public PrestarLibroModelImpl(PrestarLibroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void prestarLibro(Context context, Libro libro, int id_usuario) {
        conn = ConexionSQLiteHelper.getInstance(context);
        if (Validaciones.verificarPrestamo(context, id_usuario, libro)) {
            if (Validaciones.validarPrestamo(context, libro)) {
                long res = conn.prestarLibro(libro, id_usuario);
                if (res > 0) {
                    presenter.mostrarResultado("Se prestó correctamente el libro");
                } else {
                    presenter.mostrarError("No se prestó el libro");
                }
            } else {
                presenter.mostrarError("Cantidad de libros no disponible para prestar");
            }
        } else {
            presenter.mostrarError("Ya haz registrado este libro previamente");
        }
    }
}
