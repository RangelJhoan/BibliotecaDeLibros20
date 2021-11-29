package com.example.bibliotecadelibros20.view.fragments.usuario.librosdisponibles;

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

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentUsuLibrosDisponiblesBinding;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;
import com.example.bibliotecadelibros20.presenter.AdminPresenterImpl;
import com.example.bibliotecadelibros20.view.adapters.AdaptadorLibrosDisponibles;

import java.util.ArrayList;

public class UsuLibrosDisponiblesFragment extends Fragment implements UsuLibrosDisponiblesMVP.View {

    FragmentUsuLibrosDisponiblesBinding binding;
    AdaptadorLibrosDisponibles adaptadorLibrosDisponibles;
    View vista;
    UsuLibrosDisponiblesMVP.Presenter presenter;

    public UsuLibrosDisponiblesFragment() {
    }

    public static UsuLibrosDisponiblesFragment newInstance() {
        UsuLibrosDisponiblesFragment fragment = new UsuLibrosDisponiblesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsuLibrosDisponiblesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vista = view;
        presenter = new UsuLibrosDisponiblesPresenterImpl(this);
        presenter.consultarLibros(getContext());

        final NavController navController = Navigation.findNavController(view);
        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_lector);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.usuMisLibrosFragment);
        });

    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {
        binding.rvLibrosDisponibles.setLayoutManager(new GridLayoutManager(getContext(),2));
        adaptadorLibrosDisponibles = new AdaptadorLibrosDisponibles(getContext(),listaLibros);
        adaptadorLibrosDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(vista);
                Bundle bundle = new Bundle();
                bundle.putSerializable("libro",listaLibros.get(binding.rvLibrosDisponibles.getChildAdapterPosition(v)));
                navController.navigate(R.id.usuPrestarLibroFragment,bundle);
            }
        });

        binding.rvLibrosDisponibles.setAdapter(adaptadorLibrosDisponibles);
    }
}