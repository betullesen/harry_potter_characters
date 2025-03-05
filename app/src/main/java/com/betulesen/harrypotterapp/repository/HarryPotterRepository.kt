package com.betulesen.harrypotterapp.repository

import android.util.Log
import com.betulesen.harrypotterapp.model.HarryPotterList
import com.betulesen.harrypotterapp.model.HarryPotterListItem
import com.betulesen.harrypotterapp.service.HarryPotterAPI
import com.betulesen.harrypotterapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.truncate

@Singleton
class HarryPotterRepository @Inject constructor(
    private val api : HarryPotterAPI
){
    suspend fun getCharacters() : Resource<HarryPotterList>{
        val response  = try {
            api.getAllCharacters()
        }catch (e : Exception){
            return Resource.Error("Error")
        }
        return Resource.Success(response)

    }

    suspend fun getCharacterDetail(id: String): Resource<HarryPotterListItem> {
        val response = try {
            api.getAllCharacters()
        } catch (e: Exception) {
            return Resource.Error("Error fetching data: ${e.message}")
        }


        val character = response.find { it.name == id }

        return if (character != null) {
            Resource.Success(character)
        } else {
            Resource.Error("Character not found")
        }
    }


}