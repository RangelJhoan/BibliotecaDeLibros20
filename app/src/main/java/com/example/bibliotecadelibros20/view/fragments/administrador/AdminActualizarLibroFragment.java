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
import android.widget.Toast;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentAdminActualizarLibroBinding;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;
import com.example.bibliotecadelibros20.presenter.AdminPresenterImpl;

import java.util.ArrayList;

public class AdminActualizarLibroFragment extends Fragment implements AdminView {
    FragmentAdminActualizarLibroBinding binding;
    AdminPresenter presenter;

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
        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_administrador);
        presenter = new AdminPresenterImpl(this);

        Bundle bundle = getArguments();
        if(bundle != null){
            Libro libro = (Libro) bundle.getSerializable("libro");
            Toast.makeText(getContext(),libro.getTitulo(),Toast.LENGTH_SHORT).show();
            binding.btnActualizarLibro.setOnClickListener(v -> {
                presenter.actualizarLibro(getContext(),libro);
            });
        }

        NavController navController = Navigation.findNavController(view);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosDisponiblesFragment);
        });

    }

    @Override
    public void mostrarResultado(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {

    }
}