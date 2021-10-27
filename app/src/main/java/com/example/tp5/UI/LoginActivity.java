package com.example.tp5.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp5.ApiService;
import com.example.tp5.Models.Pokemon;
import com.example.tp5.Models.Usuario;
import com.example.tp5.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btnLoginIngresar;
    private TextView edtLoginUsuario, edtLoginClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.findViews();
    }

    private void findViews() {

        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);
        edtLoginUsuario = findViewById(R.id.edtLoginUsuario);
        edtLoginClave = findViewById(R.id.edtLoginClave);

        btnLoginIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarDatos()) {
                    String usuario = edtLoginUsuario.getText().toString();
                    String clave = edtLoginClave.getText().toString();
                    cargarUsuario(usuario, clave);
                }
            }
        });

    }

    private boolean validarDatos(){

        if(edtLoginUsuario.getText().toString().isEmpty()){
            edtLoginUsuario.setError("Esta dato es obligatorio");
            return false;
        }

        if(edtLoginClave.getText().toString().isEmpty()){
            edtLoginClave.setError("Este dato es obligatorio");
            return false;
        }

        return true;
    }

    private void cargarUsuario(String usuario, String clave){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.116:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<Boolean> call = postService.login(usuario, clave);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body()){
                    limpiarControles();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario y Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Error, algo salio mal",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void limpiarControles(){
        edtLoginUsuario.setText("");
        edtLoginClave.setText("");
    }
}