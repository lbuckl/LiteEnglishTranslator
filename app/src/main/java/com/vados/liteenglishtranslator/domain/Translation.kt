package com.vados.liteenglishtranslator.domain

import com.google.gson.annotations.SerializedName


class Translation(
    @field:SerializedName("text") val translation: String?
)
