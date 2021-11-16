package com.kumail.cakes.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by kumailhussain on 16/11/2021.
 */
@JsonClass(generateAdapter = true)
data class Cake(
    @Json(name = "title")
    val title: String,
    @Json(name = "desc")
    val description: String,
    @Json(name = "image")
    val imageUrl: String
)