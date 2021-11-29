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
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotecadelibros20.conexion.ConexionSQLiteHelper;
import com.example.bibliotecadelibros20.databinding.ActivityLoginBinding;
import com.example.bibliotecadelibros20.entidades.Usuario;
import com.example.bibliotecadelibros20.utilidades.Sesion;
import com.example.bibliotecadelibros20.utilidades.UtilidadesDB;
import com.example.bibliotecadelibros20.utilidades.Validaciones;
import com.example.bibliotecadelibros20.view.activities.AdministradorActivity;
import com.example.bibliotecadelibros20.view.activities.UsuarioActivity;
import com.example.bibliotecadelibros20.view.activities.registro.RegistrarseActivity;

public class LoginActivity extends AppCompatActivity implements LoginMVP.View{
    ConexionSQLiteHelper conn;
    ActivityLoginBinding binding;
    LoginMVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        conn = ConexionSQLiteHelper.getInstance(getApplicationContext());
        presenter = new LoginPresenterImpl(this);

        removerSesion();

        generarSpan();

        binding.btnIniciarSesion.setOnClickListener(v -> {
            iniciarSesion();
        });

    }

    private void iniciarSesion() {
        EditText[] editTexts = {binding.etCorreo,binding.etClave};
        if(Validaciones.validarCampos(editTexts)){
            presenter.confirmarInicioSesion(getApplicationContext(),binding.etCorreo.getText().toString(),binding.etClave.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(),"Por favor llene todos los datos", Toast.LENGTH_LONG).show();
        }
    }

    private void removerSesion() {
        Sesion.usuario = new Usuario();
    }

    private void generarSpan() {
        String link = "¿No estás registrado? Crear Cuenta";

        SpannableString ss = new SpannableString(link);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrarseActivity.class);
                startActivity(intent);
                finish();
            }
        };

        ss.setSpan(clickableSpan1, 22, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        binding.tvCrearCuenta.setText(ss);
        binding.tvCrearCuenta.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void mostrarConfirmacion(int tipo_usuario) {
        if(tipo_usuario == 1){
            startActivity(new Intent(LoginActivity.this, UsuarioActivity.class));
            finish();
        }else if(tipo_usuario == 2){
            startActivity(new Intent(LoginActivity.this, AdministradorActivity.class));
            finish();
        }
    }

    @Override
    public void mostrarError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
    }
}