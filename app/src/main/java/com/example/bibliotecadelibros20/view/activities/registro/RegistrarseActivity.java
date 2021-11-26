package com.example.bibliotecadelibros20.view.activities.registro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.example.bibliotecadelibros20.databinding.ActivityRegistrarseBinding;
import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.view.activities.Login.LoginActivity;

public class RegistrarseActivity extends AppCompatActivity implements RegistroMVP.View{
    ActivityRegistrarseBinding binding;
    RegistroMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        generarSpan();

        binding = ActivityRegistrarseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegistrarsePresenterImpl(this);
        Usuario usuario = new Usuario();
        usuario.setNombre(binding.etNombre.getText().toString());
        usuario.setCorreo_electronico(binding.etCorreo.getText().toString());
        usuario.setTelefono(binding.etTelefono.getText().toString());
        usuario.setDireccion(binding.etDireccion.getText().toString());
        usuario.setClave(binding.etClave.getText().toString());
        presenter.registrarUsuario(this,usuario);

    }

    @Override
    public void mostrarConfirmacion(String confirmacion) {
        Toast.makeText(this, confirmacion, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegistrarseActivity.this, LoginActivity.class));
    }

    @Override
    public void mostrarError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void generarSpan() {
        String link = "¿Tienes cuenta? Inicia Sesión";

        SpannableString ss = new SpannableString(link);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(RegistrarseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 16, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvIniciarSesion.setText(ss);
        binding.tvIniciarSesion.setMovementMethod(LinkMovementMethod.getInstance());
    }

}