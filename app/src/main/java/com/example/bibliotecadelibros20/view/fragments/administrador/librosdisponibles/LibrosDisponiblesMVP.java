package com.example.bibliotecadelibros20.view.fragments.administrador.librosdisponibles;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public interface LibrosDisponiblesMVP {
    interface View{
        void mostrarLibros(ArrayList<Libro> listaLibros);
    }
    interface Presenter{
        void mostrarLibros(ArrayList<Libro> listaLibros);
        void consultarLibrosDisponibles(Context context);
    }
    interface Model{
        void consultarLibrosDisponibles(Context context);
    }
}
