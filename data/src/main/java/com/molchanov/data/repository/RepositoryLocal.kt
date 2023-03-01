package com.molchanov.data.repository

import com.molchanov.domain.model.domain.DataModel

interface RepositoryLocal<T> {

    suspend fun getData(word: String): T

    suspend fun saveDataToDB(translations: DataModel)
}