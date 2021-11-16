package com.kumail.cakes.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kumail.cakes.R
import timber.log.Timber

/**
 * Created by kumailhussain on 16/11/2021.
 */
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    try {
        when {
            url.isNullOrBlank() -> {
                this.setImageResource(R.drawable.ic_birthday_cake_placeholder)
            }
            else -> {
                Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.ic_birthday_cake_placeholder)
                    .into(this)
            }
        }
    } catch (ex: Exception) {
        Timber.e(ex, "Unable to load image url: $url")
    }
}