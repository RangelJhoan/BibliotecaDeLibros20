package com.example.bibliotecadelibros20.view.activities.Login;

import android.content.Context;

public interface LoginMVP {
    interface View{
        void mostrarConfirmacion(int tipo_usuario);
        void mostrarError(String error);
    }
    interface Presenter{
        void mostrarConfirmacion(int tipo_usuario);
        void mostrarError(String error);
        void confirmarInicioSesion(Context context, String correo, String clave);
    }
    interface Model{
        void confirmarInicioSesion(Context context, String correo, String clave);
    }
}
