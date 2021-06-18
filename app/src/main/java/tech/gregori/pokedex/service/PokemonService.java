package tech.gregori.pokedex.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tech.gregori.pokedex.model.PokemonList;

public interface PokemonService {
    @GET("pokedex.json")
    Call<PokemonList> getPokemonList();
}
