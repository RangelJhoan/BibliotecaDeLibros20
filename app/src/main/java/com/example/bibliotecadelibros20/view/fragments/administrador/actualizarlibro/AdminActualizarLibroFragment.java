package com.example.bibliotecadelibros20.view.fragments.administrador.actualizarlibro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentAdminActualizarLibroBinding;
import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

public class AdminActualizarLibroFragment extends Fragment implements ActualizarLibroMVP.View {
    FragmentAdminActualizarLibroBinding binding;
    ActualizarLibroMVP.Presenter presenter;
    Libro libro;
    Libro libroAntiguo = null;
    NavController navController;

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
        presenter = new ActualizarLibroPresenterImpl(this);
        navController = Navigation.findNavController(view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            libro = (Libro) bundle.getSerializable("libro");

            binding.etNombre.setText(libro.getTitulo());
            binding.etAutor.setText(libro.getAutor().getNombre());
            binding.etCantidadLibros.setText(String.valueOf(libro.getCantidad()));
            binding.etUrlLibro.setText(libro.getUrl());
            binding.etImagen.setText(libro.getImagen());
            binding.etDescripcion.setText(libro.getDescripcion());

        }

        binding.btnActualizarLibro.setOnClickListener(v -> {
            actualizarLibro();
        });

        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosDisponiblesFragment);
        });

    }

    private void actualizarLibro() {

        try {
            libroAntiguo = libro.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        EditText[]
        editTexts = {binding.etNombre, binding.etAutor, binding.etCantidadLibros, binding.etUrlLibro, binding.etImagen, binding.etDescripcion};
        if (Validaciones.validarCampos(editTexts)) {
            Autor autor = new Autor();
            Libro libroNuevo = new Libro();
            libroNuevo.setTitulo(binding.etNombre.getText().toString());
            autor.setNombre(binding.etAutor.getText().toString());
            libroNuevo.setAutor(autor);
            libroNuevo.setCantidad(Integer.parseInt(binding.etCantidadLibros.getText().toString()));
            libroNuevo.setUrl(binding.etUrlLibro.getText().toString());
            libroNuevo.setImagen(binding.etImagen.getText().toString());
            libroNuevo.setDescripcion(binding.etDescripcion.getText().toString());

            presenter.actualizarLibro(getContext(), libroNuevo, libroAntiguo);
        } else {
            Toast.makeText(getContext(), "Por favor llene todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void mostrarResultado(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.adminLibrosDisponiblesFragment);
    }

    @Override
    public void mostrarError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }
}