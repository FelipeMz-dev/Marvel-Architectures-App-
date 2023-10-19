package com.mz_dev.architecturesapplication.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import com.mz_dev.architecturesapplication.data.Character
import kotlinx.coroutines.flow.Flow

@Database(entities = [LocalCharacter::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun getDao(): MarvelDao
}

@Dao
interface MarvelDao {

    @Query("SELECT * FROM LocalCharacter")
    fun getCharacters(): Flow<List<LocalCharacter>>

    @Insert
    suspend fun insertAll(characters: List<LocalCharacter>)

    @Update
    suspend fun updateCharacter(character: LocalCharacter)

    @Query("SELECT COUNT(*) FROM LocalCharacter")
    suspend fun countCharacters(): Int
}