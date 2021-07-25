package com.colemichaels.jetpackdemo.models

import com.squareup.moshi.Json

data class Game(
    @Json(name = "name")
    val mName: String = "",
    @Json(name = "deck")
    val mDeck: String = "",
    @Json(name = "image")
    val mImage: Image = Image()
) {
    val name get() = mName
    val deck get() = mDeck
    val image get() = mImage
}
