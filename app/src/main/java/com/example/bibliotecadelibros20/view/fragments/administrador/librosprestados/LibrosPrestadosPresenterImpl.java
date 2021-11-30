package com.example.bibliotecadelibros20.view.fragments.administrador.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;

public class LibrosPrestadosPresenterImpl implements LibrosPrestadosMVP.Presenter{

    private LibrosPrestadosMVP.View view;
    private LibrosPrestadosMVP.Model model;

    public LibrosPrestadosPresenterImpl(LibrosPrestadosMVP.View view) {
        this.view = view;
        model = new LibrosPrestadosModelImpl(this);
    }

    @Override
    public void mostrarPrestamo(ArrayList<Prestamo> listaPrestamo) {
        if(view != null){
            view.mostrarPrestamo(listaPrestamo);
        }
    }

    @Override
    public void consultarPrestamo(Context context) {
        if(model != null){
            model.consultarPrestamo(context);
        }
    }
}
