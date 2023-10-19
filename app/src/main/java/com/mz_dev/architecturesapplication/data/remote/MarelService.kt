package com.mz_dev.architecturesapplication.data.remote

import com.mz_dev.architecturesapplication.data.remote.characterResult.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MarelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int
    ): CharacterResult
}