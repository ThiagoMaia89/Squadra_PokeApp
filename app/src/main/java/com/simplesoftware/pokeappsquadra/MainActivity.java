package com.simplesoftware.pokeappsquadra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    public void irPokemons(View view) {

        startActivity(new Intent(MainActivity.this, PokemonActivity.class));

    }

    public void irTipos(View view) {

        startActivity(new Intent(MainActivity.this, TypeActivity.class));

    }

}