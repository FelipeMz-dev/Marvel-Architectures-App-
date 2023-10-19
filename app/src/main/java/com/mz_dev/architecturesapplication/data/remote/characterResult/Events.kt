package com.mz_dev.architecturesapplication.data.remote.characterResult

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)