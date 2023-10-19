package com.mz_dev.architecturesapplication.data.remote.characterResult

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ServerCharacter>,
    val total: Int
)