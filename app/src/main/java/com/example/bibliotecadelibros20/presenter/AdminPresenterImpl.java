package com.example.bibliotecadelibros20.presenter;

import android.content.Context;

import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.interactor.AdminInteractorImpl;
import com.example.bibliotecadelibros20.interfaces.AdminInteractor;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;

import java.util.ArrayList;

public class AdminPresenterImpl implements AdminPresenter {

    private AdminView view;
    private AdminInteractor interactor;

    public AdminPresenterImpl(AdminView view) {
        this.view = view;
        this.interactor = new AdminInteractorImpl(this);
    }

    @Override
    public void mostrarResultado(String resultado) {
        if (view != null) {
            view.mostrarResultado(resultado);
        }
    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {
        if (view != null) {
            view.mostrarLibros(listaLibros);
        }
    }

    @Override
    public void agregarLibro(Context context, Libro libro) {
        if (interactor != null) {
            interactor.agregarLibro(context, libro);
        }
    }

    @Override
    public void consultarLibros(Context context) {
        if (interactor != null) {
            interactor.consultarLibros(context);
        }
    }

    @Override
    public void actualizarLibro(Context context, Libro libro) {
        if (interactor != null) {
            interactor.actualizarLibro(context, libro);
        }
    }

    @Override
    public void prestarLibro(Context context, Libro libro, int id_usuario) {
        if(interactor != null){
            interactor.prestarLibro(context, libro, id_usuario);
        }
    }

    @Override
    public void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo) {
        if(view != null){
            view.mostrarLibrosPrestados(listaPrestamo);
        }
    }

    @Override
    public void consultarLibrosPrestadosUsu(Context context, int id_usuario) {
        if(interactor != null){
            interactor.consultarLibrosPrestadosUsu(context,id_usuario);
        }
    }


}
