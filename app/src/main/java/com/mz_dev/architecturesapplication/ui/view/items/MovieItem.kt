package com.mz_dev.architecturesapplication.ui.view.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mz_dev.architecturesapplication.data.Character

@Composable
fun MovieItem(item: Character, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box {
            AsyncImage(
                model = item.thumbnail,
                contentDescription = item.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1 / 1f)
            )
            if (item.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = Color.Yellow,
                    contentDescription = "Favorite",
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
        Text(
            text = item.name,
            modifier = Modifier.padding(5.dp)
        )
    }
}