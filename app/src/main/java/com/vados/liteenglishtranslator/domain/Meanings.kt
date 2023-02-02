package com.vados.liteenglishtranslator.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Meanings(
    val translation: Translation?,
    val imageUrl: String?
): Parcelable
