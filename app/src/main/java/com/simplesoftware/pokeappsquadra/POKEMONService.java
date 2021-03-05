package com.simplesoftware.pokeappsquadra;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface POKEMONService {


    @GET("pokemon?offset=0&limit=150")
    Call<PokeResposta> buscarListaPokemon();

    @GET("type/")
    Call<TypeResposta> buscarListaTipos();


}
