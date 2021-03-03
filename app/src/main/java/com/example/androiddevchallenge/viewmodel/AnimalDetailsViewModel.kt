package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.*
import com.example.androiddevchallenge.data.AnimalsRepository
import com.example.androiddevchallenge.data.FakeAnimalRepository
import com.example.androiddevchallenge.entities.Animal
import kotlinx.coroutines.launch

class AnimalDetailsViewModel(private val repository: AnimalsRepository): ViewModel() {

    private val _animal = MutableLiveData<Animal>()
    val animal: LiveData<Animal>
        get() = _animal

    fun refresh(id: Int) {
        viewModelScope.launch {
            _animal.value = repository.getAnimal(id)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class AnimaDetailsViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalDetailsViewModel::class.java)) {
            return AnimalDetailsViewModel(FakeAnimalRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}