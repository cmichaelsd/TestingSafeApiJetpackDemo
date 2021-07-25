package com.colemichaels.jetpackdemo.models

import com.squareup.moshi.Json

data class Image(
    @Json(name = "original_url")
    val mOriginalUrl: String = "",
    @Json(name = "thumb_url")
    val mThumbUrl: String = ""
) {
    val originalUrl get() = mOriginalUrl
    val thumbUrl get() = mThumbUrl
}
