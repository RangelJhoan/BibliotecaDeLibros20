package com.example.bibliotecadelibros20.view.fragments.usuario;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentUsuMisLibrosBinding;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;
import com.example.bibliotecadelibros20.interfaces.IComunicaFragments;
import com.example.bibliotecadelibros20.presenter.AdminPresenterImpl;
import com.example.bibliotecadelibros20.utilidades.Sesion;
import com.example.bibliotecadelibros20.view.adapters.AdaptadorPrestamosLL;

import java.util.ArrayList;

public class UsuMisLibrosFragment extends Fragment implements AdminView {

    FragmentUsuMisLibrosBinding binding;
    IComunicaFragments iComunicaFragments;
    Activity actividad;
    AdaptadorPrestamosLL adaptadorPrestamosLL;
    AdminPresenter presenter;

    public UsuMisLibrosFragment() {
    }

    public static UsuMisLibrosFragment newInstance() {
        UsuMisLibrosFragment fragment = new UsuMisLibrosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsuMisLibrosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AdminPresenterImpl(this);
        presenter.consultarLibrosPrestadosUsu(getContext(), Sesion.usuario.getId());
        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_lector);
        final NavController navController = Navigation.findNavController(view);
        binding.toolbar.btnMas.setOnClickListener(v -> {
            iComunicaFragments.abrirDialog(v);
        });
        binding.prestarfooter.btnPrestar.setOnClickListener(v -> {
            navController.navigate(R.id.usuLibrosDisponiblesFragment);
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
            iComunicaFragments = (IComunicaFragments) actividad;
        }
    }

    @Override
    public void mostrarResultado(String resultado) {

    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {

    }

    @Override
    public void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo) {
        binding.rvLibrosPrestados.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorPrestamosLL = new AdaptadorPrestamosLL(getContext(), listaPrestamo);
        binding.rvLibrosPrestados.setAdapter(adaptadorPrestamosLL);
    }
}