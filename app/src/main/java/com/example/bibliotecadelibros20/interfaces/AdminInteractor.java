package com.example.bibliotecadelibros20.interfaces;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;

public interface AdminInteractor {
    void agregarLibro(Context context, Libro libro);
    void actualizarLibro(Context context, Libro libro);
    void consultarLibros(Context context);
    void consultarLibrosPrestados(Context context);
    void consultarLibrosPrestadosUsu(Context context, int id_usuario);
    void prestarLibro(Context context, Libro libro, int id_usuario);
}
