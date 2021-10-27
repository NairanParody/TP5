package com.example.tp5.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tp5.Adapter.PokemonAdapter;
import com.example.tp5.ApiService;
import com.example.tp5.Models.Pokemon;
import com.example.tp5.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaPokemon;
    private PokemonAdapter adapter;
    private List<Pokemon> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<Pokemon>();

        obtenerListaApi();

        adapter = new PokemonAdapter(lista);

        listaPokemon = findViewById(R.id.lvPokemon);

        listaPokemon.setAdapter(adapter);

        listaPokemon.setOnItemClickListener(this);

    }
    private void obtenerListaApi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.5.116:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call< List<Pokemon> > call = postService.getPokemon();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                for(Pokemon pokemon :  response.body()) {
                    lista.add(pokemon);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, "Seleccionó ID: "+listaPost.get(position).getId(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        intent.putExtra("KEY_ID", lista.get(position).getId());
        startActivity(intent);
    }

}