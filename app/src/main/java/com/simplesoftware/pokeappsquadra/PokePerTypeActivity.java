package com.simplesoftware.pokeappsquadra;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class PokePerTypeActivity extends AppCompatActivity {
    TextView tv_teste;
    ImageView imageType;
    private Retrofit retrofit;
    private RecyclerView recyclerview;
    private RecyclerAdapterPokemon recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_per_type);
        tv_teste = findViewById(R.id.tv_teste);
        recyclerview = findViewById(R.id.recyclerview);
        imageType = findViewById(R.id.imageType);

        setTitle("Tipo");


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tv_teste.setText(obterNome());

        setarImagem();


    }

    private String obterNome() {
        Bundle extras = getIntent().getExtras();
        String name = "";
        if (extras != null) {
            name = extras.getString("name");
        }
        return name;
    }

    private void setarImagem(){
        switch (obterNome()){
            case "normal": imageType.setImageResource(R.drawable.normal);
            break;
            case "fighting": imageType.setImageResource(R.drawable.fighting);
            break;
            case "flying": imageType.setImageResource(R.drawable.flying);
            break;
            case "poison": imageType.setImageResource(R.drawable.poison);
            break;
            case "ground": imageType.setImageResource(R.drawable.ground);
            break;
            case "rock": imageType.setImageResource(R.drawable.rock);
            break;
            case "bug": imageType.setImageResource(R.drawable.bug);
            break;
            case "ghost": imageType.setImageResource(R.drawable.ghost);
            break;
            case "steel": imageType.setImageResource(R.drawable.steel);
            break;
            case "fire": imageType.setImageResource(R.drawable.fire);
            break;
            case "water": imageType.setImageResource(R.drawable.water);
            break;
            case "grass": imageType.setImageResource(R.drawable.grass);
            break;
            case "electric": imageType.setImageResource(R.drawable.eletric);
            break;
            case "psychic": imageType.setImageResource(R.drawable.psychic);
            break;
            case "ice": imageType.setImageResource(R.drawable.ice);
            break;
            case "dragon": imageType.setImageResource(R.drawable.dragon);
            break;
            case "dark": imageType.setImageResource(R.drawable.dark);
            break;
            case "fairy": imageType.setImageResource(R.drawable.fairy);
            break;
            case "shadow": imageType.setImageResource(R.drawable.shadow);
            break;


        }
    }

}

