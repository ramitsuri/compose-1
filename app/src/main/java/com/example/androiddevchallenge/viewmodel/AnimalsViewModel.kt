package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.*
import com.example.androiddevchallenge.data.AnimalsRepository
import com.example.androiddevchallenge.data.FakeAnimalRepository
import com.example.androiddevchallenge.entities.Animal
import kotlinx.coroutines.launch

class AnimalsViewModel(private val repository: AnimalsRepository): ViewModel() {

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>>
        get() = _animals

    fun refresh() {
        viewModelScope.launch {
            _animals.value = repository.getAnimals()
        }
    }
}

@Suppress("UNCHECKED_CAST")
class AnimalsViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalsViewModel::class.java)) {
            return AnimalsViewModel(FakeAnimalRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}