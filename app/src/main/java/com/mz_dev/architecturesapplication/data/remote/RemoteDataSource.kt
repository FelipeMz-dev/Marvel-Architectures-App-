package com.mz_dev.architecturesapplication.data.remote

import com.mz_dev.architecturesapplication.data.Character
import com.mz_dev.architecturesapplication.data.remote.characterResult.toCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {
    private val baseURL = "https://gateway.marvel.com/v1/public/"
    private val auth = MarvelApiAuth()
    private val apiKey = auth.getPublicApiKey()
    private val timestamp = auth.generateTimestamp()
    private val hash = auth.generateHash(timestamp)
    private val limit = 20

    suspend fun getCharacters(): List<Character> {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarelService::class.java)
            .getCharacters(apiKey, timestamp, hash, limit)
            .data
            .results
            .map { it.toCharacter() }
    }
}