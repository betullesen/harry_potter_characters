package com.betulesen.harrypotterapp.service

import com.betulesen.harrypotterapp.model.HarryPotterList
import retrofit2.http.GET

interface HarryPotterAPI {

    @GET("api/characters")
    suspend fun getAllCharacters(): HarryPotterList
}