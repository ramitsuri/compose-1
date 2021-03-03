package com.example.androiddevchallenge.api

import com.example.androiddevchallenge.entities.Animal
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.time.Instant

interface NetworkApi {
    @GET("animals")
    suspend fun getAnimals(): Response<List<Animal>>

    companion object {
        private const val BASE_URL = "https://api.petfinder.com/v2/animals"

        /*fun create(dateTimeHelper: DateTimeHelper): NetworkApi {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val gson = GsonBuilder()
                .registerTypeAdapter(
                    Instant::class.java,
                    InstantAdapter(dateTimeHelper)
                )
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NetworkApi::class.java)
        }*/
    }
}