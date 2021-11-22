package com.example.bibliotecadelibros20.view.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecadelibros20.databinding.ActivityAdministradorBinding;
import com.example.bibliotecadelibros20.dialogos.DialogoAdminOpcFragment;
import com.example.bibliotecadelibros20.interfaces.IComunicaFragments;

public class AdministradorActivity extends AppCompatActivity implements IComunicaFragments {

    ActivityAdministradorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdministradorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void abrirAdminDialog(View navView) {
        DialogoAdminOpcFragment dialogoAdminOpcFragment = new DialogoAdminOpcFragment(navView);
        dialogoAdminOpcFragment.show(getSupportFragmentManager(),"DialogoAdmOpc");
    }
}