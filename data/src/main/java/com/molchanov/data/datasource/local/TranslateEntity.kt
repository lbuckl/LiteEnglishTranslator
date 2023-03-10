package com.molchanov.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Класс данных хранения переведённых слов
 */
@Entity(tableName = "Translated_words") //
data class TranslateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "translate")
    val translate: String,
    @ColumnInfo(name = "Url")
    val pictureUrl: String
)