package com.kumail.cakes.util

import androidx.fragment.app.Fragment
import com.kumail.cakes.data.model.Cake
import com.kumail.cakes.data.model.ErrorResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

/**
 * Created by kumailhussain on 16/11/2021.
 */
fun Fragment.setToolbarTitle(title: String) {
    activity?.title = title
}

fun String.parseErrorJson(): ErrorResponse {
    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
    val errorResponse = adapter.fromJson(this)

    return errorResponse ?: ErrorResponse()
}

fun List<Cake>.formatCakes(): List<Cake> {
    return this.distinct().sortedBy { it.title }
}