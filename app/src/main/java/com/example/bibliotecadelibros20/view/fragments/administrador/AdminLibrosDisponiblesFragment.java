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
import com.example.bibliotecadelibros20.databinding.FragmentAdminLibrosDisponiblesBinding;

public class AdminLibrosDisponiblesFragment extends Fragment {
    FragmentAdminLibrosDisponiblesBinding binding;

    public AdminLibrosDisponiblesFragment() {

    }

    public static AdminLibrosDisponiblesFragment newInstance(String param1, String param2) {
        AdminLibrosDisponiblesFragment fragment = new AdminLibrosDisponiblesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminLibrosDisponiblesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        binding.btnAgregarLibro.setOnClickListener(v -> {
            navController.navigate(R.id.adminAgregarLibroFragment);
        });

    }
}