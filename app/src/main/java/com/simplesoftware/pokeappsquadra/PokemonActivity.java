package com.simplesoftware.pokeappsquadra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView rv_recyclerview;
    private RecyclerAdapterPokemon recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        setTitle("Pokémons");

        instanciarComponentes();

        recyclerAdapter = new RecyclerAdapterPokemon(this);
        rv_recyclerview.setAdapter(recyclerAdapter);
        rv_recyclerview.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rv_recyclerview.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obterDados();

    }

    public void instanciarComponentes() {
        rv_recyclerview = findViewById(R.id.rv_recyclerview);
    }

    private void obterDados() {
        POKEMONService service = retrofit.create(POKEMONService.class);
        Call<PokeResposta> pokeRespostaCall = service.buscarListaPokemon();

        pokeRespostaCall.enqueue(new Callback<PokeResposta>() {
            @Override
            public void onResponse(Call<PokeResposta> call, Response<PokeResposta> response) {
                if (response.isSuccessful()) {
                    PokeResposta pokeResposta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokeResposta.getResults();

                    recyclerAdapter.adicionarListaPokemon(listaPokemon);

                } else {
                    Toast.makeText(PokemonActivity.this, "onResponse: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokeResposta> call, Throwable t) {
                Toast.makeText(PokemonActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public void home(MenuItem item) {
        startActivity(new Intent(PokemonActivity.this, MainActivity.class));
    }
}