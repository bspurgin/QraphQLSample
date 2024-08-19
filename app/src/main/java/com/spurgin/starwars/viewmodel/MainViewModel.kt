package com.spurgin.starwars.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spurgin.starwars.GetPeopleQuery
import com.spurgin.starwars.GetPersonQuery
import com.spurgin.starwars.repository.StarWarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class MainViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _people = MutableStateFlow<List<GetPeopleQuery.Person?>>(emptyList())
    val people: StateFlow<List<GetPeopleQuery.Person?>> = _people

    private val _selectedPerson = MutableStateFlow<GetPersonQuery.Person?>(null)
    val selectedPerson: StateFlow<GetPersonQuery.Person?> = _selectedPerson

    init {
        Log.d("ZZZ MainViewModel", "Init")
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            Log.d("ZZZ MainViewModel", "Fetching people")
            _people.value = repository.getPeople()
        }
    }

    fun fetchPersonDetail(id: String) {
        viewModelScope.launch {
            Log.d("ZZZ MainViewModel", "Fetching details for $id")
            _selectedPerson.value = repository.getPersonDetail(id)
        }
    }
}

