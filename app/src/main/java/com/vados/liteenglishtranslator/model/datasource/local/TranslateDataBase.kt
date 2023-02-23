package com.vados.liteenglishtranslator.model.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TranslateEntity::class], version = 1)
abstract class TranslateDataBase: RoomDatabase() {
    abstract fun getDAO(): TranslateDAO
}
