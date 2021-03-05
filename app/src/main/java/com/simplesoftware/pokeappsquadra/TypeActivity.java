package com.simplesoftware.pokeappsquadra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TypeActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerview;
    private RecyclerAdapterType recyclerAdapter;
    private RecyclerAdapterType.RecyclerViewClickListener listener;
    ArrayList<Type> listaTipos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        setTitle("Tipos");

        instanciarComponentes();

        setOnClickListener();
        recyclerAdapter = new RecyclerAdapterType(this, listener);
        recyclerview.setAdapter(recyclerAdapter);
        recyclerview.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerview.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        obterDados();

    }


    private void setOnClickListener() {
        listener = new RecyclerAdapterType.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), PokePerTypeActivity.class);
                intent.putExtra("name", listaTipos.get(position).getName());
                startActivity(intent);
            }
        };
    }

    private void instanciarComponentes() {
        recyclerview = findViewById(R.id.recyclerview);
    }

    private void obterDados() {
        POKEMONService service = retrofit.create(POKEMONService.class);
        Call<TypeResposta> typeRespostaCall = service.buscarListaTipos();

        typeRespostaCall.enqueue(new Callback<TypeResposta>() {
            @Override
            public void onResponse(Call<TypeResposta> call, Response<TypeResposta> response) {
                if (response.isSuccessful()) {
                    TypeResposta typeResposta = response.body();
                    listaTipos = typeResposta.getResults();

                    recyclerAdapter.adicionarListaType(listaTipos);

                } else {
                    Toast.makeText(TypeActivity.this, "onResponse" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TypeResposta> call, Throwable t) {
                Toast.makeText(TypeActivity.this, "onFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(TypeActivity.this, MainActivity.class));
    }
}