package com.vados.liteenglishtranslator.model.datasource.local

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.domain.Meanings
import com.vados.liteenglishtranslator.model.domain.Translation

/**
 * Класс для реализации получения данных из БД Room
 */
class RoomDataBaseImplementation(context: Context) : DataSource<List<DataModel>> {

    private val translateDB: TranslateDataBase? = null

    override suspend fun getData(word: String): List<DataModel> {
        Log.v("@@@", "RoomDataBaseImplementation: getData")

        return listOf(
            DataModel(
            "GetFromRoomDB",
            listOf(Meanings(
                Translation("GetFromRoomDB"),
            "GetFromRoomDB"
            ))
        )
        )
    }

    /*fun getTranslateDB(): TranslateDataBase {
        if (translateDB == null){
            translateDB = Room.databaseBuilder(

            )
        }
    }*/
}
