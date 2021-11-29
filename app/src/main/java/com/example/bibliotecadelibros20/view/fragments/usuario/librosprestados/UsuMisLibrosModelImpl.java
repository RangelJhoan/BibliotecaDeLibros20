package com.example.bibliotecadelibros20.view.fragments.usuario.librosprestados;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;

public class UsuMisLibrosModelImpl implements UsuMisLibrosMVP.Model {
    private UsuMisLibrosMVP.Presenter presenter;
    private ConexionSQLiteHelper conn;

    public UsuMisLibrosModelImpl(UsuMisLibrosMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void consultarLibrosPrestados(Context context, int id_usuario) {
        conn = ConexionSQLiteHelper.getInstance(context);
        presenter.mostrarLibrosPrestados(conn.consultarLibrosPrestadosUsu(id_usuario));
    }
}
