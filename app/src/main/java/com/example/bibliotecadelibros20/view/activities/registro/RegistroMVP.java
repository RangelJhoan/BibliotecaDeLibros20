package com.example.bibliotecadelibros20.view.activities.registro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Usuario;

public interface RegistroMVP {
    interface View{
        void mostrarConfirmacion(String confirmacion);
        void mostrarError(String error);
    }
    interface Presenter{
        void mostrarConfirmacion(String confirmacion);
        void mostrarError(String error);
        void registrarUsuario(Context context, Usuario usuario);
    }
    interface Model{
        void registrarUsuario(Context context, Usuario usuario);
    }
}
