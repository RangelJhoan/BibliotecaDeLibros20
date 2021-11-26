package com.example.bibliotecadelibros20.view.activities.registro;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

public class RegistrarseModelImpl implements RegistroMVP.Model {
    ConexionSQLiteHelper conn;
    RegistroMVP.Presenter presenter;

    public RegistrarseModelImpl(RegistroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void registrarUsuario(Context context, Usuario usuario) {
        if (Validaciones.validarClave(usuario.getClave())) {
            if (Validaciones.validarDominioCorreo(usuario.getCorreo_electronico())) {
                if (Validaciones.validarCorreoUnico(context, usuario.getCorreo_electronico())) {
                    conn = ConexionSQLiteHelper.getInstance(context);
                    long resultado = conn.insertarUsuario(usuario);
                    if (resultado > 0) {
                        presenter.mostrarConfirmacion("Usuario registrado correctamente");
                    } else {
                        presenter.mostrarError("Error al registrar el usuario");
                    }
                } else {
                    presenter.mostrarError("Correo electrónico ya se encuentra registrado en el sistema");
                }
            } else {
                presenter.mostrarError("Dominio de correo no permitido, permitidos: Gmail, Hotmail y Outlook.");
            }
        } else {
            presenter.mostrarError("Clave débil. Mínimo 8 caracteres alfanuméricos incluyendo 1 caracter especial, 1 mayúscula");
        }
    }
}
