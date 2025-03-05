package com.betulesen.harrypotterapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betulesen.harrypotterapp.model.HarryPotterList
import com.betulesen.harrypotterapp.model.HarryPotterListItem
import com.betulesen.harrypotterapp.model.Wand
import com.betulesen.harrypotterapp.repository.HarryPotterRepository
import com.betulesen.harrypotterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository : HarryPotterRepository
) : ViewModel(){

    var characterList = mutableStateOf<List<HarryPotterListItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initalCharacterList = listOf<HarryPotterListItem>()
    private var isSearchStarting = true

    init {
        loadCharacters()
    }

    fun searchCharacterList(query: String){
        val listToSearch = if(isSearchStarting){
            characterList.value
        }else{
            initalCharacterList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                characterList.value = initalCharacterList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                (it.name ?: "").contains(query.trim(),ignoreCase = true)
            }

            if (isSearchStarting){
                initalCharacterList = characterList.value
                isSearchStarting = false
            }

            characterList.value = results

        }
    }

    fun loadCharacters(){
        viewModelScope.launch {
            isLoading.value = true
            val result =  repository.getCharacters()
            when(result){

                is Resource.Success -> {
                    val harryPotterItems = result.data!!.mapIndexed { index, harryPotterListItem ->
                        HarryPotterListItem(
                            id = harryPotterListItem.id ?: "",
                            name = harryPotterListItem.name ?: "Bilinmiyor",
                            actor = harryPotterListItem.actor ?: "Bilinmiyor",
                            alive = harryPotterListItem.alive ?: false,
                            house = harryPotterListItem.house ?: "Bilinmiyor",
                            species = harryPotterListItem.species ?: "Bilinmiyor",
                            patronus = harryPotterListItem.patronus ?: "Bilinmiyor",
                            image = harryPotterListItem.image ?: "",
                            ancestry = harryPotterListItem.ancestry ?: "Bilinmiyor",
                            dateOfBirth = harryPotterListItem.dateOfBirth ?: "Bilinmiyor",
                            eyeColour = harryPotterListItem.eyeColour ?: "Bilinmiyor",
                            gender = harryPotterListItem.gender ?: "Bilinmiyor",
                            hairColour = harryPotterListItem.hairColour ?: "Bilinmiyor",
                            hogwartsStaff = harryPotterListItem.hogwartsStaff ?: false,
                            hogwartsStudent = harryPotterListItem.hogwartsStudent ?: false,
                            alternate_actors = harryPotterListItem.alternate_actors ?: emptyList(),
                            alternate_names = harryPotterListItem.alternate_names ?: emptyList(),
                            wand = harryPotterListItem.wand ?: Wand("", "", ""),
                            wizard = harryPotterListItem.wizard ?: false,
                            yearOfBirth = harryPotterListItem.yearOfBirth ?: 0
                        )
                    } as List<HarryPotterListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    characterList.value += harryPotterItems
                }

                is Resource.Error -> {
                    errorMessage.value = result.message!!
                    isLoading.value =false

                }

                is Resource.Loading -> {
                    errorMessage.value = ""
                }
            }
        }
    }




















}