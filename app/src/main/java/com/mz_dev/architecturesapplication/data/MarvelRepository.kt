package com.mz_dev.architecturesapplication.data

import com.mz_dev.architecturesapplication.data.local.LocalDataSource
import com.mz_dev.architecturesapplication.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class MarvelRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val characters: Flow<List<Character>> = localDataSource.characters

    suspend fun updateCharacter(character: Character) {
        localDataSource.updateCharacter(character)
    }

    suspend fun getCharacters(){
        val isDbEmpty = localDataSource.countCharacters() == 0
        if (isDbEmpty) {
            localDataSource.insertAll(remoteDataSource.getCharacters())
        }

    }
}

