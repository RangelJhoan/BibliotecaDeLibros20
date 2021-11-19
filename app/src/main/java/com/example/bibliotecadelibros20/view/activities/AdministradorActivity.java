package com.example.bibliotecadelibros20.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.ActivityAdministradorBinding;

public class AdministradorActivity extends AppCompatActivity {

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

}