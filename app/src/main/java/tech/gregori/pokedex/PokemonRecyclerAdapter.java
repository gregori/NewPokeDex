package tech.gregori.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import tech.gregori.pokedex.model.Pokemon;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder> {
    Context context;
    List<Pokemon> pokemons;

    public PokemonRecyclerAdapter(Context context, List<Pokemon> pokemons) {
        this.context = context;
        this.pokemons = pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonRecyclerAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poke_recyclerview_layout, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonRecyclerAdapter.PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNum.setText(pokemon.getNum());
        holder.pokemonType.setText(pokemon.getType().toString());

        Picasso.Builder builder = new Picasso.Builder(context);
        // Utilizar downloader para http -> https
        builder.downloader(new OkHttp3Downloader(context))
                // carregue a url em pokemon.img
                .build().load(pokemon.getImg())
                // enquanto não baixou, use um placeholder
                .placeholder(R.drawable.baseline_image_black_48dp)
                // insira a imagem no ImageView
                .into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        if (pokemons != null) {
            return pokemons.size();
        }

        return 0;
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;
        TextView pokemonNum;
        TextView pokemonType;
        ImageView pokemonImage;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.poke_name);
            pokemonNum = itemView.findViewById(R.id.poke_num);
            pokemonType = itemView.findViewById(R.id.poke_type);
            pokemonImage = itemView.findViewById(R.id.poke_img);
        }
    }
}
