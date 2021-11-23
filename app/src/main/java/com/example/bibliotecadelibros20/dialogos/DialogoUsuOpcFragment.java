package com.example.bibliotecadelibros20.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.WindowManager;

import com.example.bibliotecadelibros20.R;
import com.example.bibliotecadelibros20.databinding.FragmentDialogoUsuOpcBinding;

public class DialogoUsuOpcFragment extends DialogFragment {

    FragmentDialogoUsuOpcBinding binding;
    Activity actividad;

    public DialogoUsuOpcFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearUsuDialogOpc();
    }

    private AlertDialog crearUsuDialogOpc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentDialogoUsuOpcBinding.inflate(getActivity().getLayoutInflater());
        builder.setView(binding.getRoot());
        eventoBotones();
        return builder.create();
    }

    private void eventoBotones() {
        binding.btnSalir.setOnClickListener(v -> {
            getActivity().finish();
            dismiss();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 350;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad = (Activity) context;
        }else{
            throw new RuntimeException(context.toString()+" must implement OnFragmentListener");
        }
    }
}