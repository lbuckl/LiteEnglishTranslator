package com.molchanov.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import coil.ImageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide

/**
 * Функция загрузки изображния по URL с помощью Glide
 */
fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context)
        .load(getValidUrl(url))
        .placeholder(R.drawable.ic_no_photo_vector)
        .into(this)
}

/**
 * Функция загрузки изображния по URL с помощью Glide
 */
fun ImageView.loadImageFromUrl(url: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo_vector)
        .into(this)
}

suspend fun <T> getImage(context: Context, url: T): Drawable {
    val loader = ImageLoader(context)

    val validUrl = "https:" + url.toString().split("https:")[1]

    val request = ImageRequest.Builder(context)
        .data(validUrl)
        .build()

    loader.enqueue(request).job.await().drawable.let {
        if (it != null) return loader.enqueue(request).job.await().drawable!!
        else return AppCompatResources.getDrawable(context, R.drawable.ic_no_photo_vector)!!
    }
}

private fun getValidUrl(url: String): String {
    val buf = url.split("https:")

    return if (buf.size > 1) "https:" + buf[1]
    else url
}