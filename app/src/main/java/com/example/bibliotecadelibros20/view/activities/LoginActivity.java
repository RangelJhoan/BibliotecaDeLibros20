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
        setContentView(R.layout.activity_login);

        binding.btnUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,UsuarioActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this,UsuarioActivity.class);
            startActivity(intent);
            finish();
        });

    }
}