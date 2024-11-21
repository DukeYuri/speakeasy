package ru.hse.speakeasy.app.screen.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.hse.speakeasy.app.core.domain.LanguageCode
import ru.hse.speakeasy.app.core.domain.history.SaveHistoryUseCase
import ru.hse.speakeasy.app.core.domain.translation.TranslationUseCase
import javax.inject.Inject

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val translationUseCase: TranslationUseCase,
    private val saveHistoryUseCase: SaveHistoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TranslationUiState())
    val uiState: StateFlow<TranslationUiState> = _uiState

    fun updateInputText(text: String) {
        _uiState.update { it.copy(inputText = text) }
    }

    fun clearInputText() {
        _uiState.update { it.copy(inputText = "") }
    }

    fun swapLanguages() {
        _uiState.update {
            it.copy(
                sourceLang = it.targetLang,
                targetLang = it.sourceLang
            )
        }
    }

    fun translateText() {
        viewModelScope.launch {
            val result = translationUseCase.translate(
                sl = LanguageCode.ENGLISH, // _uiState.value.sourceLang,
                dl = LanguageCode.RUSSIAN, // _uiState.value.targetLang,
                text = _uiState.value.inputText,
            )

            _uiState.update {
                it.copy(
                    translatedText = result.translations.possibleTranslations.firstOrNull()
                        ?: _uiState.value.inputText
                )
            }
            saveHistoryUseCase.save(_uiState.value.inputText, _uiState.value.translatedText.orEmpty())
        }
    }

}

data class TranslationUiState(
    val sourceLang: String = "English",
    val targetLang: String = "Russia",
    val inputText: String = "",
    val translatedText: String? = null,
)