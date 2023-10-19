package com.mz_dev.architecturesapplication.data.local

import com.mz_dev.architecturesapplication.data.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val dao: MarvelDao) {
    val characters: Flow<List<Character>> = dao.getCharacters().map { characters ->
        characters.map { it.toCharacter() }
    }

    suspend fun updateCharacter(character: Character) {
        dao.updateCharacter(character.toLocalCharacter())
    }

    suspend fun insertAll(characters: List<Character>) {
        dao.insertAll(characters.map { it.toLocalCharacter() })
    }

    suspend fun countCharacters(): Int {
        return dao.countCharacters()
    }
}