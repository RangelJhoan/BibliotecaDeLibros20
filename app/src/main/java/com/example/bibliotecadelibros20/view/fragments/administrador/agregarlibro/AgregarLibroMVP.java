package com.example.bibliotecadelibros20.view.fragments.administrador.agregarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public interface AgregarLibroMVP {
    interface View{
        void mostrarResultado(String resultado);
    }
    interface Presenter{
        void mostrarResultado(String resultado);
        void agregarLibro(Context context, Libro libro);
    }
    interface Model{
        void agregarLibro(Context context, Libro libro);
    }
}
