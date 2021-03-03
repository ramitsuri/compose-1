package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.entities.Animal

class MainAnimalRepository: AnimalsRepository {
    override suspend fun getAnimals(): List<Animal> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimal(id: Int): Animal {
        TODO("Not yet implemented")
    }
}