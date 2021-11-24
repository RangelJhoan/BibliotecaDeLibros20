package com.example.bibliotecadelibros20.interfaces;

import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;

public interface AdminView {
    void mostrarResultado(String resultado);
    void mostrarLibros(ArrayList<Libro> listaLibros);
    void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo);
}
