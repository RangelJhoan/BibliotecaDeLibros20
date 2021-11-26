package com.example.bibliotecadelibros20.view.fragments.usuario.prestarlibro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

public class PrestarLibroModelImpl implements PrestarLibroMVP.Model {
    private ConexionSQLiteHelper conn;
    private PrestarLibroMVP.Presenter presenter;

    public PrestarLibroModelImpl(PrestarLibroMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void prestarLibro(Context context, Libro libro, int id_usuario) {
        conn = ConexionSQLiteHelper.getInstance(context);
        if(Validaciones.validarPrestamo(context,libro)){
            if(Validaciones.verificarPrestamo(context,id_usuario,libro)){
                SQLiteDatabase db = conn.getReadableDatabase();
                ContentValues values = new ContentValues();
                values.put(UtilidadesDB.PRESTAMO_ID_LIBRO, libro.getId());
                values.put(UtilidadesDB.PRESTAMO_ID_USUARIO, id_usuario);

                long res = db.insert(UtilidadesDB.PRESTAMO_TABLA, UtilidadesDB.PRESTAMO_ID, values);

                if (res > 0) {
                    presenter.mostrarResultado("Se prestó correctamente el libro");
                } else {
                    presenter.mostrarResultado("No se prestó el libro");
                }
            }else{
                presenter.mostrarResultado("Ya haz registrado este libro previamente");
            }
        }else{
            presenter.mostrarResultado("Cantidad de libros no disponible para prestar");
        }
    }
}
