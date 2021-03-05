package com.simplesoftware.pokeappsquadra;

import java.util.ArrayList;

public class PokeResposta {

    private ArrayList<Pokemon> results;
    private ArrayList<Pokemon> types;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    public ArrayList<Pokemon> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Pokemon> types) {
        this.types = types;
    }
}
