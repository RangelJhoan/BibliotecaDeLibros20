package com.example.bibliotecadelibros20.view.fragments.usuario.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;

public class UsuMisLibrosPresenterImpl implements UsuMisLibrosMVP.Presenter {

    private UsuMisLibrosMVP.Model model;
    private UsuMisLibrosMVP.View view;

    public UsuMisLibrosPresenterImpl(UsuMisLibrosMVP.View view) {
        this.view = view;
        model = new UsuMisLibrosModelImpl(this);
    }

    @Override
    public void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo) {
        if (view != null) {
            view.mostrarLibrosPrestados(listaPrestamo);
        }
    }

    @Override
    public void consultarLibrosPrestados(Context context, int id_usuario) {
        if (model != null) {
            model.consultarLibrosPrestados(context, id_usuario);
        }
    }
}
