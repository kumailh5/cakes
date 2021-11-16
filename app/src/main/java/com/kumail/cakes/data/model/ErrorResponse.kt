package com.kumail.cakes.data.model

import com.kumail.cakes.R
import com.kumail.cakes.util.Strings
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by kumailhussain on 16/11/2021.
 */
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val errorMessage: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "code")
    val code: Int
) {
    constructor() : this(
        Strings.get(R.string.error_something_went_wrong),
        Strings.get(R.string.error), 0
    )
}