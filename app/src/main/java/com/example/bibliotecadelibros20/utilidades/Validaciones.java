package com.example.bibliotecadelibros20.utilidades;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Validaciones {

    public static boolean validarCampos(EditText[] listaTextViews){
        for (int i=0;i<listaTextViews.length;i++){
            if(listaTextViews[i].getText().toString().equals("")){
                return false;
            }
        }
        return true;
    }

}
