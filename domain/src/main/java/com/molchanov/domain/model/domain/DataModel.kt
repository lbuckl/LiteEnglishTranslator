package com.molchanov.domain.model.domain

import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)