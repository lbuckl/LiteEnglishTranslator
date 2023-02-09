package com.vados.liteenglishtranslator.utils.parsel

import com.vados.liteenglishtranslator.model.domain.AppState
import com.vados.liteenglishtranslator.model.domain.DataModel
import com.vados.liteenglishtranslator.model.domain.Meanings

/**
 * Функция проверяет результат на нулевые или пустые данные
 * и возвращет AppState только с не пустыми значениями
 */
fun parseSearchResults(state: AppState): AppState {
    val newSearchResults = arrayListOf<DataModel>()
    //Проверка на нуль общих данных
    when (state) {
        is AppState.Success -> {
            val searchResults = state.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResults)
                }
            }
        }
        else -> {
            //TODO Nothing
        }
    }

    return AppState.Success(newSearchResults)
}

/**
 * Функция проверяет данны и полей на нуль и пустые строки
 * и заполняет массив только не пустыми значениями
 */
private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }
}

/**
 * Функция преобразует массив значений(переведённых слов) в строку
 */
fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()

    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}
