package com.example.bibliotecadelibros20.view.fragments.usuario.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Prestamo;

import java.util.ArrayList;

public interface UsuMisLibrosMVP {
    interface View{
        void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo);
    }
    interface Presenter{
        void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo);
        void consultarLibrosPrestados(Context context, int id_usuario);
    }
    interface Model{
        void consultarLibrosPrestados(Context context, int id_usuario);
    }
}
