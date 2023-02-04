package com.vados.liteenglishtranslator.model.domain

import com.google.gson.annotations.SerializedName


class Translation(
    @field:SerializedName("text") val translation: String?
)
