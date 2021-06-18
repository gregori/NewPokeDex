package tech.gregori.pokedex.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonApiClient {
    public static final String BASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) { // nunca foi instanciado meu cliente
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
