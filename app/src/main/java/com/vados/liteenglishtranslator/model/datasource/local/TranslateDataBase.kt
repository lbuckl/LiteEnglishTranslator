package com.vados.liteenglishtranslator.model.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TranslateEntity::class], version = 1)
abstract class TranslateDataBase: RoomDatabase() {
    companion object{
       val TRANSLATE_DB_NAME = "TRANSLATE_DB"
    }

    abstract fun getDAO(): TranslateDAO
}
