package com.example.bibliotecadelibros20.view.fragments.administrador.agregarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public class AgregarLibroPresenterImpl implements AgregarLibroMVP.Presenter {

    AgregarLibroMVP.Model model;
    AgregarLibroMVP.View view;

    public AgregarLibroPresenterImpl(AgregarLibroMVP.View view) {
        this.view = view;
        model = new AgregarLibroModelImpl(this);
    }

    @Override
    public void mostrarResultado(String resultado) {
        if (view != null) {
            view.mostrarResultado(resultado);
        }
    }

    @Override
    public void agregarLibro(Context context, Libro libro) {
        if (model != null) {
            model.agregarLibro(context, libro);
        }
    }
}
