package ru.hse.speakeasy.app.core.domain.translation

import jakarta.inject.Inject
import ru.hse.speakeasy.app.core.TranslationApi
import ru.hse.speakeasy.app.core.TranslationResponse
import ru.hse.speakeasy.app.core.domain.LanguageCode

class TranslationUseCase @Inject constructor(
    private val translationApi: TranslationApi
) {
    suspend fun translate(sl: LanguageCode, dl: LanguageCode, text: String): TranslationResponse {
        return translationApi.translate(sl.code, dl.code, text)
    }
}