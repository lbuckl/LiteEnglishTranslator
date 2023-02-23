package com.vados.liteenglishtranslator.model.datasource.local

import androidx.room.*

@Dao
interface TranslateDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<TranslateEntity>)

    @Query("SELECT * FROM Translated_words WHERE word = :wordKey")
    fun queryAllTranslates(wordKey: String): List<TranslateEntity>
}