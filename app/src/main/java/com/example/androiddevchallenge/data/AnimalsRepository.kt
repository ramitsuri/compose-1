package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.entities.Animal

interface AnimalsRepository {
    suspend fun getAnimals(): List<Animal>

    suspend fun getAnimal(id: Int): Animal
}