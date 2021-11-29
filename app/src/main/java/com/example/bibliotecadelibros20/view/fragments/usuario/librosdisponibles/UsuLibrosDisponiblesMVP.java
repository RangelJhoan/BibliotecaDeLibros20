package com.example.bibliotecadelibros20.view.fragments.usuario.librosdisponibles;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public interface UsuLibrosDisponiblesMVP {
    interface View{
        void mostrarLibros(ArrayList<Libro> listaLibros);
    }

    interface Presenter{
        void mostrarLibros(ArrayList<Libro> listaLibros);
        void consultarLibros(Context context);
    }

    interface Model{
        void consultarLibros(Context context);
    }
}
