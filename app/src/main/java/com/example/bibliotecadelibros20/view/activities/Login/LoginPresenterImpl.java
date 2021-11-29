package com.example.bibliotecadelibros20.view.activities.Login;

import android.content.Context;

public class LoginPresenterImpl implements LoginMVP.Presenter{

    LoginMVP.Model model;
    LoginMVP.View view;

    public LoginPresenterImpl(LoginMVP.View view) {
        this.view = view;
        model = new LoginModelImp(this);
    }

    @Override
    public void mostrarConfirmacion(int tipo_usuario) {
        if(view != null){
            view.mostrarConfirmacion(tipo_usuario);
        }
    }

    @Override
    public void mostrarError(String error) {
        if(view != null){
            view.mostrarError(error);
        }
    }

    @Override
    public void confirmarInicioSesion(Context context, String correo, String clave) {
        if(model != null){
            model.confirmarInicioSesion(context, correo, clave);
        }
    }
}
