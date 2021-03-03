package com.example.androiddevchallenge.entities

import java.time.Instant

data class Animal(
    val id: Int,
    val url: String,
    val age: String,
    val gender: String,
    val size: String,
    val name: String,
    val description: String,
    val type: String,
    val photos: List<Photo>
)
