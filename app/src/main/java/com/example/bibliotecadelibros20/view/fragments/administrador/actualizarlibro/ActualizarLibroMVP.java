package com.example.bibliotecadelibros20.view.fragments.administrador.actualizarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public interface ActualizarLibroMVP {
    interface View{
        void mostrarResultado(String resultado);
    }
    interface Presenter{
        void mostrarResultado(String resultado);
        void actualizarLibro(Context context, Libro libro);
    }
    interface Model{
        void actualizarLibro(Context context, Libro libro);
    }
}
