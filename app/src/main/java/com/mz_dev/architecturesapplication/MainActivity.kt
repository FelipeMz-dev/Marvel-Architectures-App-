package com.mz_dev.architecturesapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.mz_dev.architecturesapplication.data.local.LocalDataSource
import com.mz_dev.architecturesapplication.data.MarvelRepository
import com.mz_dev.architecturesapplication.data.remote.RemoteDataSource
import com.mz_dev.architecturesapplication.data.local.MarvelDatabase
import com.mz_dev.architecturesapplication.ui.view.Main

class MainActivity : ComponentActivity() {

    private lateinit var db : MarvelDatabase

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            this,
            MarvelDatabase::class.java,
            "marvel_database"
        ).build()

        val repository = MarvelRepository(
            localDataSource = LocalDataSource(db.getDao()),
            remoteDataSource = RemoteDataSource()
        )

        setContent {
            Main(repository)
          }
    }
}