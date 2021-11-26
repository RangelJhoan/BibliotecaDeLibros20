package com.example.bibliotecadelibros20.view.activities.registro;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Usuario;

public class RegistrarsePresenterImpl implements RegistroMVP.Presenter {

    private RegistroMVP.View view;
    private RegistroMVP.Model model;

    public RegistrarsePresenterImpl(RegistroMVP.View view) {
        this.view = view;
        model = new RegistrarseModelImpl(this);
    }

    @Override
    public void mostrarConfirmacion(String confirmacion) {
        if(view != null){
            view.mostrarConfirmacion(confirmacion);
        }
    }

    @Override
    public void mostrarError(String error) {
        if(view != null){
            view.mostrarError(error);
        }
    }

    @Override
    public void registrarUsuario(Context context, Usuario usuario) {
        if(model != null){
            model.registrarUsuario(context, usuario);
        }
    }
}
