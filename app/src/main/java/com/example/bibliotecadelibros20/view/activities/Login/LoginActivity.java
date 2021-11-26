package com.example.bibliotecadelibros20.view.activities.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.bibliotecadelibros20.databinding.ActivityLoginBinding;
import com.example.bibliotecadelibros20.view.activities.registro.RegistrarseActivity;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        generarSpan();

        binding.btnIniciarSesion.setOnClickListener(v -> {
            
        });

    }

    private void generarSpan() {
        String link = "¿No estás registrado? Crear Cuenta";

        SpannableString ss = new SpannableString(link);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrarseActivity.class);
                startActivity(intent);
            }
        };

        ss.setSpan(clickableSpan1, 22, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvCrearCuenta.setText(ss);
        binding.tvCrearCuenta.setMovementMethod(LinkMovementMethod.getInstance());
    }

}