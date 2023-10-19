package com.mz_dev.architecturesapplication.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mz_dev.architecturesapplication.data.MarvelRepository
import com.mz_dev.architecturesapplication.data.local.MarvelDao
import com.mz_dev.architecturesapplication.ui.view.items.MovieItem
import com.mz_dev.architecturesapplication.ui.theme.ArchitecturesApplicationTheme
import com.mz_dev.architecturesapplication.ui.viewModel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(repository: MarvelRepository) {
    ArchitecturesApplicationTheme {
        val viewModel: MainViewModel = viewModel { MainViewModel(repository) }
        val state by viewModel.state.collectAsState()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Marvel Characters") }) }
            ) { padding ->
                if (state.loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if (state.characters.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(180.dp),
                        modifier = Modifier.padding(padding),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(state.characters) {
                            MovieItem(
                                item = it,
                                onClick = { viewModel.onChangeFavorite(it) })
                        }
                    }
                }
            }
        }
    }
}