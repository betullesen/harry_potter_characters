package com.betulesen.harrypotterapp.viewmodel

import androidx.lifecycle.ViewModel
import com.betulesen.harrypotterapp.model.HarryPotterList
import com.betulesen.harrypotterapp.model.HarryPotterListItem
import com.betulesen.harrypotterapp.repository.HarryPotterRepository
import com.betulesen.harrypotterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: HarryPotterRepository
) : ViewModel() {

    suspend fun getCharactersDetail(id : String) : Resource<HarryPotterListItem>{
        return repository.getCharacterDetail(id)
    }
}