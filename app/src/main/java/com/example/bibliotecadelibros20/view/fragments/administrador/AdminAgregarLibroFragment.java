package com.example.bibliotecadelibros20.view.fragments.administrador;

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
import com.example.bibliotecadelibros20.databinding.FragmentAdminAgregarLibroBinding;
import com.example.bibliotecadelibros20.entidades.Autor;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.entidades.Prestamo;
import com.example.bibliotecadelibros20.interfaces.AdminPresenter;
import com.example.bibliotecadelibros20.interfaces.AdminView;
import com.example.bibliotecadelibros20.presenter.AdminPresenterImpl;
import com.example.bibliotecadelibros20.utilidades.Validaciones;

import java.util.ArrayList;

public class AdminAgregarLibroFragment extends Fragment implements AdminView {
    private AdminPresenter presenter;
    FragmentAdminAgregarLibroBinding binding;

    public AdminAgregarLibroFragment() {

    }

    public static AdminAgregarLibroFragment newInstance() {
        AdminAgregarLibroFragment fragment = new AdminAgregarLibroFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAdminAgregarLibroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_administrador);
        presenter = new AdminPresenterImpl(this);
        final NavController navController = Navigation.findNavController(view);

        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.adminLibrosDisponiblesFragment);
        });

        binding.btnAgregarLibro.setOnClickListener(view1 -> {
            EditText[] editTexts = {binding.etNombre, binding.etDescripcion, binding.etUrlLibro, binding.etCantidadLibros, binding.etImagen, binding.etAutor};
            if (Validaciones.validarCampos(editTexts)) {
                agregarLibro();
                navController.navigate(R.id.adminLibrosDisponiblesFragment);
            } else {
                Toast.makeText(getContext(), "Por favor llene todos los datos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void agregarLibro() {
        Libro libro = new Libro();
        Autor autor = new Autor();

        autor.setNombre(binding.etAutor.getText().toString());
        libro.setTitulo(binding.etNombre.getText().toString());
        libro.setDescripcion(binding.etDescripcion.getText().toString());
        libro.setUrl(binding.etUrlLibro.getText().toString());
        libro.setCantidad(Integer.parseInt(binding.etCantidadLibros.getText().toString()));
        libro.setImagen(binding.etImagen.getText().toString());
        libro.setAutor(autor);

        presenter.agregarLibro(getContext(), libro);

    }

    @Override
    public void mostrarResultado(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarLibros(ArrayList<Libro> listaLibros) {

    }

    @Override
    public void mostrarLibrosPrestados(ArrayList<Prestamo> listaPrestamo) {

    }
}