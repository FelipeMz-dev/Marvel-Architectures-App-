package com.mz_dev.architecturesapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mz_dev.architecturesapplication.data.Character

@Entity
data class LocalCharacter(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val favorite: Boolean = false
)

fun LocalCharacter.toCharacter() = Character(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    favorite = favorite
)

fun Character.toLocalCharacter() = LocalCharacter(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    favorite = favorite
)