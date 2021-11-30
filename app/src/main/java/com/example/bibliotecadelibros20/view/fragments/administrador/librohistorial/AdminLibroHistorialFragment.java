package com.example.bibliotecadelibros20.view.fragments.administrador.librohistorial;

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
import com.example.bibliotecadelibros20.databinding.FragmentAdminLibroHistorialBinding;

public class AdminLibroHistorialFragment extends Fragment {
    FragmentAdminLibroHistorialBinding binding;

    public AdminLibroHistorialFragment() {

    }

    public static AdminLibroHistorialFragment newInstance(String param1, String param2) {
        AdminLibroHistorialFragment fragment = new AdminLibroHistorialFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminLibroHistorialBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosPrestadosFragment);
        });

    }



}