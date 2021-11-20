package com.example.bibliotecadelibros20.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,UsuarioActivity.class);
            startActivity(intent);
        });

        binding.btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,AdministradorActivity.class);
            startActivity(intent);
        });

    }

}