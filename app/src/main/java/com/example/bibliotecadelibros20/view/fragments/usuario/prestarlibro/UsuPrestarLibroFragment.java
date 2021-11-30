package com.example.bibliotecadelibros20.view.fragments.usuario.prestarlibro;

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

import com.bumptech.glide.Glide;
import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentUsuPrestarLibroBinding;
import com.example.bibliotecadelibros20.entidades.Libro;
import com.example.bibliotecadelibros20.utilidades.Sesion;

public class UsuPrestarLibroFragment extends Fragment implements PrestarLibroMVP.View {

    FragmentUsuPrestarLibroBinding binding;
    PrestarLibroMVP.Presenter presenter;
    NavController navController;
    Libro libro;

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
        presenter = new PrestarLibroPresenterImpl(this);

        binding.toolbar.ivPerfil.setImageResource(R.drawable.icon_lector);
        binding.toolbar.btnMas.setVisibility(View.GONE);
        binding.toolbar.btnAtras.setVisibility(View.VISIBLE);
        navController = Navigation.findNavController(view);
        binding.toolbar.btnAtras.setOnClickListener(v -> {
            navController.navigate(R.id.usuLibrosDisponiblesFragment);
        });

        mostrarLibro();

        binding.footer.btnPrestar.setOnClickListener(v -> {
            prestarLibro();
        });

    }

    private void mostrarLibro() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            libro = (Libro) bundle.getSerializable("libro");
            Glide.with(getContext())
                    .load(libro.getImagen())
                    .error(R.drawable.ic_error_imagen_24dp)
                    .into(binding.ivPortada);
            binding.tvTituloLibro.setText(libro.getTitulo());
            binding.tvAutorLibro.setText(libro.getAutor().getNombre());
            binding.tvDescripcion.setText(libro.getDescripcion());
        }
    }

    private void prestarLibro() {
        presenter.prestarLibro(getContext(), libro, Sesion.usuario.getId());
    }

    @Override
    public void mostrarResultado(String resultado) {
        Toast.makeText(getContext(), resultado, Toast.LENGTH_SHORT).show();
        navController.navigate(R.id.usuMisLibrosFragment);
    }

    @Override
    public void mostrarError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
    //Atribución error: <a href="https://es.vecteezy.com/vectores-gratis/dise%C3%B1o">Diseño Vectores por Vecteezy</a>
}