package com.mz_dev.architecturesapplication.data.remote.characterResult

data class CharacterResult(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)