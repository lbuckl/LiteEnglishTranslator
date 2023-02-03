package com.vados.liteenglishtranslator.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Translation(
    @field:SerializedName("text") val translation: String?
) : Parcelable
