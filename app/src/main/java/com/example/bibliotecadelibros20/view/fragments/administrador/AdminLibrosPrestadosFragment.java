package com.example.bibliotecadelibros20.view.fragments.administrador;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentAdminAgregarLibroBinding;
import com.example.bibliotecadelibros20.databinding.FragmentAdminLibrosPrestadosBinding;

public class AdminLibrosPrestadosFragment extends Fragment {
    FragmentAdminLibrosPrestadosBinding binding;

    public AdminLibrosPrestadosFragment() {

    }

    public static AdminLibrosPrestadosFragment newInstance(String param1, String param2) {
        AdminLibrosPrestadosFragment fragment = new AdminLibrosPrestadosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminLibrosPrestadosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosDisponiblesFragment);
        });

        binding.btnHistorial.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibroHistorialFragment);
        });

    }
}