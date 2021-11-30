package com.example.bibliotecadelibros20.view.fragments.administrador.librosprestados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentAdminLibrosPrestadosBinding;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.view.adapters.AdaptadorPrestamosGL;

import java.util.ArrayList;

public class AdminLibrosPrestadosFragment extends Fragment implements LibrosPrestadosMVP.View, SearchView.OnQueryTextListener {
    FragmentAdminLibrosPrestadosBinding binding;
    AdaptadorPrestamosGL adaptadorPrestamosGL;
    LibrosPrestadosMVP.Presenter presenter;
    View vista;

    public AdminLibrosPrestadosFragment() {

    }

    public static AdminLibrosPrestadosFragment newInstance(String param1, String param2) {
        AdminLibrosPrestadosFragment fragment = new AdminLibrosPrestadosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminLibrosPrestadosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vista = view;
        presenter = new LibrosPrestadosPresenterImpl(this);
        presenter.consultarPrestamo(getContext());

        binding.svBuscar.setOnQueryTextListener(this);

        NavController navController = Navigation.findNavController(view);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosDisponiblesFragment);
        });
    }

    @Override
    public void mostrarPrestamo(ArrayList<Prestamo> listaPrestamo) {
        binding.rvLibrosPrestados.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adaptadorPrestamosGL = new AdaptadorPrestamosGL(getContext(), listaPrestamo);
        adaptadorPrestamosGL.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(vista);
            Bundle bundle = new Bundle();
            bundle.putSerializable("prestamo", listaPrestamo.get(binding.rvLibrosPrestados.getChildAdapterPosition(view)));
            navController.navigate(R.id.adminLibroHistorialFragment, bundle);
        });
        binding.rvLibrosPrestados.setAdapter(adaptadorPrestamosGL);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adaptadorPrestamosGL.filtrado(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adaptadorPrestamosGL.filtrado(s);
        return false;
    }
}