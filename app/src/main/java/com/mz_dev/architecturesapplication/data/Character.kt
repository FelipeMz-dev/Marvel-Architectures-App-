package com.mz_dev.architecturesapplication.data

import androidx.room.Entity
import com.mz_dev.architecturesapplication.data.local.LocalCharacter

@Entity
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val favorite: Boolean = false
)