package com.example.bibliotecadelibros20.view.fragments.usuario.librosdisponibles;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public class UsuLibrosDisponiblesPresenterImpl implements UsuLibrosDisponiblesMVP.Presenter{
    UsuLibrosDisponiblesMVP.Model model;
    UsuLibrosDisponiblesMVP.View view;

    public UsuLibrosDisponiblesPresenterImpl(UsuLibrosDisponiblesMVP.View view) {
        this.view = view;
        model = new UsuLibrosDisponiblesModelImpl(this);
    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {
        if(view != null){
            view.mostrarLibros(listaLibros);
        }
    }

    @Override
    public void consultarLibros(Context context) {
        if(model != null){
            model.consultarLibros(context);
        }
    }
}
