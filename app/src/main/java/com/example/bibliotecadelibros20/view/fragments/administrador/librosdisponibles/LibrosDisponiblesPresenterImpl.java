package com.example.bibliotecadelibros20.view.fragments.administrador.librosdisponibles;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public class LibrosDisponiblesPresenterImpl implements LibrosDisponiblesMVP.Presenter{

    LibrosDisponiblesMVP.Model model;
    LibrosDisponiblesMVP.View view;

    public LibrosDisponiblesPresenterImpl(LibrosDisponiblesMVP.View view) {
        this.view = view;
        model = new LibrosDisponiblesModelImpl(this);
    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {
        if(view != null){
            view.mostrarLibros(listaLibros);
        }
    }

    @Override
    public void consultarLibrosDisponibles(Context context) {
        if(model != null){
            model.consultarLibrosDisponibles(context);
        }
    }
}
