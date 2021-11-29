package com.example.bibliotecadelibros20.view.activities.registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecadelibros20.databinding.ActivityRegistrarseBinding;
import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.utilidades.Validaciones;
import com.example.bibliotecadelibros20.view.activities.Login.LoginActivity;

public class RegistrarseActivity extends AppCompatActivity implements RegistroMVP.View {
    ActivityRegistrarseBinding binding;
    RegistroMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrarseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        generarSpan();

        presenter = new RegistrarsePresenterImpl(this);

        binding.btnRegistrar.setOnClickListener(view -> {
            registrarUsuario();
        });

    }

    private void registrarUsuario() {
        EditText[] editTexts = {binding.etNombre, binding.etCorreo, binding.etTelefono, binding.etDireccion, binding.etClave};
        if (Validaciones.validarCampos(editTexts)) {
            Usuario usuario = new Usuario();
            usuario.setNombre(binding.etNombre.getText().toString());
            usuario.setCorreo_electronico(binding.etCorreo.getText().toString());
            usuario.setTelefono(binding.etTelefono.getText().toString());
            usuario.setDireccion(binding.etDireccion.getText().toString());
            usuario.setClave(binding.etClave.getText().toString());
            presenter.registrarUsuario(this, usuario);
        } else {
            Toast.makeText(getApplicationContext(),"Por favor llene todos los campos",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void mostrarConfirmacion(String confirmacion) {
        Toast.makeText(this, confirmacion, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegistrarseActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void mostrarError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegistrarseActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void generarSpan() {
        String link = "¿Tienes cuenta? Inicia Sesión";

        SpannableString ss = new SpannableString(link);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(RegistrarseActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        ss.setSpan(clickableSpan1, 16, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvIniciarSesion.setText(ss);
        binding.tvIniciarSesion.setMovementMethod(LinkMovementMethod.getInstance());
    }

}