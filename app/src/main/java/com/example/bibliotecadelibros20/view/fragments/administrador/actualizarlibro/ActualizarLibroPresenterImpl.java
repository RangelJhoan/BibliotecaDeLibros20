package com.example.bibliotecadelibros20.view.fragments.administrador.actualizarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public class ActualizarLibroPresenterImpl implements ActualizarLibroMVP.Presenter{

    ActualizarLibroMVP.View view;
    ActualizarLibroMVP.Model model;

    public ActualizarLibroPresenterImpl(ActualizarLibroMVP.View view) {
        this.view = view;
        model = new ActualizarLibroModelImpl(this);
    }

    @Override
    public void mostrarResultado(String resultado) {
        if(view != null){
            view.mostrarResultado(resultado);
        }
    }

    @Override
    public void mostrarError(String error) {
        if(view != null){
            view.mostrarError(error);
        }
    }

    @Override
    public void actualizarLibro(Context context, Libro libroNuevo, Libro libroAntiguo) {
        if(model != null){
            model.actualizarLibro(context, libroNuevo, libroAntiguo);
        }
    }
}
