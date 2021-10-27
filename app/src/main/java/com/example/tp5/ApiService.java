package com.example.tp5;

import com.example.tp5.Models.Pokemon;
import com.example.tp5.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("pokemon")
    Call<List<Pokemon>> getPokemon();

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") int pokemonId);

    @POST("usuario")
    Call<Boolean> login(@Query("nombre")String nombre,@Query("clave")String clave);
}
