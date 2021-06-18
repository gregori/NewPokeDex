package tech.gregori.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

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
        pokemonList = new PokemonList(new ArrayList<>());

        // obtém e prepara a recyclerView
        RecyclerView recyclerView = findViewById(R.id.poke_recyclerview);
        PokemonRecyclerAdapter recyclerAdapter = new PokemonRecyclerAdapter(getApplicationContext(),
                pokemonList.getPokemon());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        PokemonService service = PokemonApiClient.getClient().create(PokemonService.class);
        Call<PokemonList> call = service.getPokemonList();
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                pokemonList = response.body();
                Log.d(TAG, "onResponse: " + pokemonList);
                // atualiza lista de pokemons quando terminar o download
                recyclerAdapter.setPokemons(pokemonList.getPokemon());
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString(), t);
            }
        });
    }
}