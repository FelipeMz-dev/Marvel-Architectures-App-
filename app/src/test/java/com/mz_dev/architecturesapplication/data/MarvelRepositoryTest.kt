package com.mz_dev.architecturesapplication.data

import com.mz_dev.architecturesapplication.data.local.LocalDataSource
import com.mz_dev.architecturesapplication.data.remote.RemoteDataSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verifyBlocking

class MarvelRepositoryTest {
    @Test
    fun `When DB is empty, server is called`() {
        val localDataSource = mock<LocalDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val remoteDataSource = mock<RemoteDataSource>()
        val repository = MarvelRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        runBlocking { repository.getCharacters() }

        verifyBlocking(remoteDataSource) { getCharacters() }
    }

    @Test
    fun `When DB is empty, movies are saved into DB`() {
        val expectedCharacters = listOf(
            Character(
                id = 0,
                name = "name",
                description = "description",
                thumbnail = "thumbnail",
                favorite = false
            )
        )
        val localDataSource = mock<LocalDataSource> {
            onBlocking { countCharacters() } doReturn 0
        }
        val remoteDataSource = mock<RemoteDataSource> {
            onBlocking { getCharacters() } doReturn expectedCharacters
        }
        val repository = MarvelRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        runBlocking { repository.getCharacters() }

        verifyBlocking(localDataSource) { insertAll(expectedCharacters) }
    }

    @Test
    fun `When DB is not empty, remote data source is not called`() {
        val localDataSource = mock<LocalDataSource> {
            onBlocking { countCharacters() } doReturn 1
        }
        val remoteDataSource = mock<RemoteDataSource>()
        val repository = MarvelRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        runBlocking { repository.getCharacters() }

        verifyBlocking(remoteDataSource, times(0)) { getCharacters() }
    }

    @Test
    fun `When DB is not empty, characters are recovered from DB`() {
        val localCharacters = listOf(
            Character(1, "name", "description", "thumbnail", false)
        )
        val remoteCharacters = listOf(
            Character(2, "name2", "description2", "thumbnail2", false)
        )
        val localDataSource = mock<LocalDataSource> {
            onBlocking { countCharacters() } doReturn 1
            onBlocking { characters } doReturn flowOf(localCharacters)
        }
        val remoteDataSource = mock<RemoteDataSource> {
            onBlocking { getCharacters() } doReturn remoteCharacters
        }
        val repository = MarvelRepository(localDataSource, remoteDataSource)

        val result = runBlocking {
            repository.getCharacters()
            repository.characters.first()
        }

        assertEquals(localCharacters, result)
    }
}