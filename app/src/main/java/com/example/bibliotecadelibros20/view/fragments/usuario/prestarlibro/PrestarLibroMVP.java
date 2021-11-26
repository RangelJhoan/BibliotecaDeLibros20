package com.example.bibliotecadelibros20.view.fragments.usuario.prestarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public interface PrestarLibroMVP {
    interface View{
        void mostrarResultado(String resultado);
    }

    interface Presenter{
        void mostrarResultado(String resultado);
        void prestarLibro(Context context, Libro libro, int id_usuario);
    }

    interface Model{
        void prestarLibro(Context context, Libro libro, int id_usuario);
    }

}
