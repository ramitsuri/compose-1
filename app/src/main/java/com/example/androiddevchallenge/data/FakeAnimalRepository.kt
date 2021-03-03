package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.entities.Animal
import kotlinx.coroutines.delay

class FakeAnimalRepository: AnimalsRepository {
    override suspend fun getAnimals(): List<Animal> {
        return animals
    }

    override suspend fun getAnimal(id: Int): Animal {
        return animals.first {
            it.id == id
        }
    }
}