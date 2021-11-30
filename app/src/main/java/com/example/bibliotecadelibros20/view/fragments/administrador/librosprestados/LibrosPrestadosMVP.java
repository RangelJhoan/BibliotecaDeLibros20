package com.example.bibliotecadelibros20.view.fragments.administrador.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;

public interface LibrosPrestadosMVP {
    interface View {
        void mostrarPrestamo(ArrayList<Prestamo> listaPrestamo);
    }

    interface Presenter {
        void mostrarPrestamo(ArrayList<Prestamo> listaPrestamo);
        void consultarPrestamo(Context context);
    }

    interface Model {
        void consultarPrestamo(Context context);
    }
}
