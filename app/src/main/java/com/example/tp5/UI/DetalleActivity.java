package com.example.tp5.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tp5.ApiService;
import com.example.tp5.Models.Pokemon;
import com.example.tp5.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleActivity extends AppCompatActivity {

    private TextView textListaId, textListaNombre, textListaTipo;
    private RatingBar barListaAtaque, barListaDefensa, barListaResistencia;
    private ImageView imageListaFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle extras = getIntent().getExtras();
        int pokemonId = extras.getInt("KEY_ID");

        findViewsById();

        cargarPokemon(pokemonId);
    }

    private void findViewsById() {
        textListaId = findViewById(R.id.textListaId);
        textListaNombre = findViewById(R.id.textListaNombre);
        textListaTipo = findViewById(R.id.textListaTipo);
        barListaAtaque = findViewById(R.id.barListaAtaque);
        barListaDefensa = findViewById(R.id.barListaDefensa);
        barListaResistencia = findViewById(R.id.barListaResistencia);
        imageListaFoto = findViewById(R.id.imageListaFoto);
    }

    private void cargarPokemon(int id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.116:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<Pokemon> call = postService.getPokemonById(id);

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon = (Pokemon) response.body();
                textListaId.setText(String.valueOf(pokemon.getId()));
                textListaNombre.setText(pokemon.getNombre());
                textListaTipo.setText(pokemon.getTipo() + ", " + pokemon.getTipo2());
                barListaAtaque.setRating((float) pokemon.getAtaque());
                barListaResistencia.setRating((float) pokemon.getResistencia());
                barListaDefensa.setRating((float) pokemon.getDefensa());
                Picasso.get()
                        .load(pokemon.getFoto())
                        .into(imageListaFoto);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
            }
        });
    }
}