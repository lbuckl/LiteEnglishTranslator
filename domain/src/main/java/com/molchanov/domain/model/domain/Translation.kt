package com.molchanov.domain.model.domain

import com.google.gson.annotations.SerializedName


class Translation(
    @field:SerializedName("text") val translation: String?
)
