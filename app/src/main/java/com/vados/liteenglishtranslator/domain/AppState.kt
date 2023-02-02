package com.vados.liteenglishtranslator.domain

/**
 * Класс состояний активити/фрагмента
 * Success - успешное выполнение
 * Error - неудачное завершение операции
 * Loading - состояние загрузки
 */
sealed class AppState {
    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}