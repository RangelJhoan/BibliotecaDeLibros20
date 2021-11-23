package com.example.bibliotecadelibros20.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.ActivityUsuarioBinding;
import com.example.bibliotecadelibros20.dialogos.DialogoUsuOpcFragment;
import com.example.bibliotecadelibros20.interfaces.IComunicaFragments;

public class UsuarioActivity extends AppCompatActivity implements IComunicaFragments {
    ActivityUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


    @Override
    public void abrirDialog(View view) {
        DialogoUsuOpcFragment dialogoUsuOpcFragment = new DialogoUsuOpcFragment();
        dialogoUsuOpcFragment.show(getSupportFragmentManager(),"DialogoUsuOpc");
    }
}