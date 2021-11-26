package com.example.bibliotecadelibros20.view.fragments.usuario.prestarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public class PrestarLibroPresenterImpl implements PrestarLibroMVP.Presenter {

    private PrestarLibroMVP.View view;
    private PrestarLibroMVP.Model model;

    public PrestarLibroPresenterImpl(PrestarLibroMVP.View view) {
        this.view = view;
        model = new PrestarLibroModelImpl(this);
    }

    @Override
    public void mostrarResultado(String resultado) {
        if (view != null) {
            view.mostrarResultado(resultado);
        }
    }

    @Override
    public void prestarLibro(Context context, Libro libro, int id_usuario) {
        if(model != null){
            model.prestarLibro(context, libro, id_usuario);
        }
    }
}
