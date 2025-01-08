package ru.hse.speakeasy.app.core.domain.history

import ru.hse.speakeasy.app.core.data.entity.TranslationHistoryEntity
import ru.hse.speakeasy.app.core.data.dao.TranslationHistoryDao
import javax.inject.Inject

class SaveHistoryUseCase @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao,
) {
    suspend fun save(sourceText: String, translatedText: String) {
        translationHistoryDao.insertHistory(
            TranslationHistoryEntity(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}