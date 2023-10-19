package com.mz_dev.architecturesapplication.data.remote.characterResult

import com.mz_dev.architecturesapplication.data.Character

data class ServerCharacter(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>,
    val favorite: Boolean = false
)

fun ServerCharacter.toCharacter() = Character(
    id = 0,
    name = name,
    description = description,
    thumbnail = thumbnail.path + "." + thumbnail.extension,
    favorite = favorite
)