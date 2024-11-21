package ru.hse.speakeasy.app.core.domain.history

import ru.hse.speakeasy.app.core.data.TranslationHistory
import ru.hse.speakeasy.app.core.data.TranslationHistoryDao
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao,
) {
    suspend fun save(sourceText: String, translatedText: String) {
        translationHistoryDao.insertHistory(
            TranslationHistory(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}