package com.example.bibliotecadelibros20.view.fragments.administrador.actualizarlibro;

import android.content.Context;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;

public class ActualizarLibroModelImpl implements ActualizarLibroMVP.Model{

    ActualizarLibroMVP.Presenter presenter;
    ConexionSQLiteHelper conn;

    public ActualizarLibroModelImpl(ActualizarLibroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void actualizarLibro(Context context, Libro libroNuevo, Libro libroAntiguo) {
        conn = ConexionSQLiteHelper.getInstance(context);
        long respuesta = conn.actualizarLibro(context, libroNuevo, libroAntiguo);
        if (respuesta > 0) {
            presenter.mostrarResultado("Se editó el libro correctamente");
        } else {
            if(respuesta == -1){
                presenter.mostrarError("La cantidad de libros del libro a editar no puede ser menor a la cantidad de libros ya prestados");
            }else if(respuesta == -2){
                presenter.mostrarError("El nombre del libro y el nombre del autor coinciden con otro libro, indique la edición");
            }
        }
    }
}
