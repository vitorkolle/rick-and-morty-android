package com.example.rickandmorty.service

import com.example.rickandmorty.model.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {
    @GET("episode/{id}")
    fun getEpisodeById(@Path("id") id: Int): Call<Episode>
}