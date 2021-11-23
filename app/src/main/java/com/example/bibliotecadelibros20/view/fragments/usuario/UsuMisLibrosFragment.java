package com.example.bibliotecadelibros20.view.fragments.usuario;

import android.app.Activity;
import android.content.Context;
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
import com.example.bibliotecadelibros20.databinding.FragmentUsuMisLibrosBinding;
import com.example.bibliotecadelibros20.interfaces.IComunicaFragments;

public class UsuMisLibrosFragment extends Fragment {

    FragmentUsuMisLibrosBinding binding;
    IComunicaFragments iComunicaFragments;
    Activity actividad;

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
}