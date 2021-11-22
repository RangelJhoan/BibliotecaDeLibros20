package com.example.bibliotecadelibros20.interfaces;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

import java.util.ArrayList;

public interface AdminPresenter {
    //IView
    void mostrarResultado(String resultado);
    void mostrarLibros(ArrayList<Libro> listaLibros);

    //IInteractor
    void agregarLibro(Context context, Libro libro);
    void consultarLibros(Context context);
    void actualizarLibro(Context context, Libro libro);
}
