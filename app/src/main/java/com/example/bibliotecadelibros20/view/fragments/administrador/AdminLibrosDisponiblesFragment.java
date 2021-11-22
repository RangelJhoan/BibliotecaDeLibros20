package com.example.bibliotecadelibros20.view.fragments.administrador;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentAdminLibrosDisponiblesBinding;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;
import com.example.bibliotecadelibros20.interfaces.IComunicaFragments;
import com.example.bibliotecadelibros20.presenter.AdminPresenterImpl;
import com.example.bibliotecadelibros20.view.adapters.AdaptadorLibrosDisponibles;

import java.util.ArrayList;

public class AdminLibrosDisponiblesFragment extends Fragment implements AdminView {

    private AdminPresenter presenter;
    FragmentAdminLibrosDisponiblesBinding binding;
    AdaptadorLibrosDisponibles adaptadorLibrosDisponibles;
    IComunicaFragments iComunicaFragments;
    Activity actividad;
    View vista;

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

        presenter = new AdminPresenterImpl(this);
        presenter.consultarLibros(getContext());
        vista = view;

        abrirOpciones(view);
    }

    private void abrirOpciones(View navView){
        binding.toolbar.btnMas.setOnClickListener(view -> {
            iComunicaFragments.abrirAdminDialog(navView);
        });
    }

    @Override
    public void mostrarResultado(String resultado) {

    }

    @Override
    public void mostrarLibros(ArrayList<Libro> libro) {
        binding.rvLibrosDisponibles.setLayoutManager(new GridLayoutManager(getContext(),2));
        adaptadorLibrosDisponibles = new AdaptadorLibrosDisponibles(getContext(),libro);
        adaptadorLibrosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(vista);
                Bundle bundle = new Bundle();
                bundle.putSerializable("libro",libro.get(binding.rvLibrosDisponibles.getChildAdapterPosition(v)));
                navController.navigate(R.id.adminActualizarLibroFragment,bundle);
            }
        });

        binding.rvLibrosDisponibles.setAdapter(adaptadorLibrosDisponibles);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            actividad = (Activity) context;
            iComunicaFragments = (IComunicaFragments) actividad;
        }
    }
}