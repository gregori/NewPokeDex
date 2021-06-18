package tech.gregori.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.gregori.pokedex.model.PokemonList;
import tech.gregori.pokedex.service.PokemonApiClient;
import tech.gregori.pokedex.service.PokemonService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private PokemonList pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokemonService service = PokemonApiClient.getClient().create(PokemonService.class);
        Call<PokemonList> call = service.getPokemonList();
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                pokemonList = response.body();
                Log.d(TAG, "onResponse: " + pokemonList);
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString(), t);
            }
        });
    }
}