package com.example.bibliotecadelibros20.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.ActivityAdministradorBinding;
import com.example.bibliotecadelibros20.databinding.FragmentDialogoAdminOpcBinding;

public class DialogoAdminOpcFragment extends DialogFragment {

    FragmentDialogoAdminOpcBinding binding;

    public DialogoAdminOpcFragment() {

    }

    public static DialogoAdminOpcFragment newInstance() {
        DialogoAdminOpcFragment fragment = new DialogoAdminOpcFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearAdminDialogOpc();
    }

    private Dialog crearAdminDialogOpc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        binding = FragmentDialogoAdminOpcBinding.inflate(inflater);
        builder.setView(binding.getRoot());

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones() {
        binding.btnAgregar.setOnClickListener(v -> {

        });

        binding.btnPrestados.setOnClickListener(v -> {

        });

        binding.btnSalir.setOnClickListener(v -> {
            getActivity().finish();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}