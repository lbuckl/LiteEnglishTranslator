package com.vados.liteenglishtranslator.model.datasource.local

import android.content.Context
import androidx.room.Room
import com.vados.liteenglishtranslator.model.datasource.DataSource
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.domain.Meanings
import com.vados.liteenglishtranslator.model.domain.Translation
import com.vados.liteenglishtranslator.utils.parsel.getEmptyDataModel

/**
 * Класс для реализации получения данных из БД Room
 */
class RoomDataBaseImplementation(private val context: Context) : DataSource<List<DataModel>> {

    private val translateDB: TranslateDataBase? = null

    override suspend fun getData(word: String): List<DataModel> {

        return listOf(dbToDataModel(getTranslateDB().getDAO().queryAllTranslates(word)))
    }

    fun setData(translations: DataModel) {
        getTranslateDB().getDAO().insertAll(
            dataModelToDb(translations)
        )
    }

    private fun getTranslateDB(): TranslateDataBase {
        return if (translateDB == null) {
            Room.databaseBuilder(
                context,
                TranslateDataBase::class.java,
                TranslateDataBase.TRANSLATE_DB_NAME
            ).build()
        } else translateDB
    }

    private fun dataModelToDb(translations: DataModel): List<TranslateEntity> {
        val result = mutableListOf<TranslateEntity>()
        val listSize = translations.meanings!!.size

        for (i in 0 until listSize) {
            result.add(
                TranslateEntity(
                    0,
                    translations.text!!,
                    translations.meanings[i].translation!!.translation!!,
                    translations.meanings[i].imageUrl!!
                )
            )
        }

        return result
    }

    private fun dbToDataModel(data: List<TranslateEntity>): DataModel {
        return if (data.isEmpty()) getEmptyDataModel()
        else DataModel(
            data[0].word,
            data.map {
                Meanings(
                    Translation(it.translate),
                    it.pictureUrl
                )
            }
        )
    }
}
