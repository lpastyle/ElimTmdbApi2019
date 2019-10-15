package com.lpastyle.elimtmdbapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ITmdbApi {

    String KEY="f8c59b73c44d9240c1ded0a07da0d5f5";

    // TMDB API call example:
    // https://api.themoviedb.org/3/configuration?api_key=f8c59b73c44d9240c1ded0a07da0d5f5
    // https://api.themoviedb.org/3/person/popular?api_key=f8c59b73c44d9240c1ded0a07da0d5f5&page=1

    @GET("person/popular")
    Call<PersonPopularResponse> getPersonPopular(
            @retrofit2.http.Query("api_key") String apiKey,
            @retrofit2.http.Query("page") String pageNb
    );
}
