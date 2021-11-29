package com.example.bibliotecadelibros20.view.activities.Login;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;

public class LoginModelImp implements LoginMVP.Model {
    ConexionSQLiteHelper conn;
    LoginMVP.Presenter presenter;

    public LoginModelImp(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void confirmarInicioSesion(Context context, String correo, String clave) {
        conn = ConexionSQLiteHelper.getInstance(context);
        int tipo_usuario = conn.iniciarSesion(correo,clave);
        if(tipo_usuario != 0){
            presenter.mostrarConfirmacion(tipo_usuario);
        }else{
            presenter.mostrarError("Correo electrónico o clave incorrecta");
        }
    }
}
