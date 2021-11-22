package com.example.bibliotecadelibros20.view.fragments.usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentUsuPrestarLibroBinding;

public class UsuPrestarLibroFragment extends Fragment {

    FragmentUsuPrestarLibroBinding binding;

    public UsuPrestarLibroFragment() {

    }

    public static UsuPrestarLibroFragment newInstance() {
        UsuPrestarLibroFragment fragment = new UsuPrestarLibroFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsuPrestarLibroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_lector);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnMas.setVisibility(View.GONE);

    }
}