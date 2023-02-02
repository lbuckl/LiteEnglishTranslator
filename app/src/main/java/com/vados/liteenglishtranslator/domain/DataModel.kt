package com.vados.liteenglishtranslator.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DataModel(
    val text: String?,
    val meanings: List<Meanings>?
): Parcelable