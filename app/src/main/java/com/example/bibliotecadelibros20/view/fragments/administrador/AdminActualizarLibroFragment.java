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
import com.example.bibliotecadelibros20.databinding.FragmentAdminActualizarLibroBinding;

public class AdminActualizarLibroFragment extends Fragment {
    FragmentAdminActualizarLibroBinding binding;

    public AdminActualizarLibroFragment() {

    }

    public static AdminActualizarLibroFragment newInstance(String param1, String param2) {
        AdminActualizarLibroFragment fragment = new AdminActualizarLibroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminActualizarLibroBinding.inflate(inflater, container, false);
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

    }
}