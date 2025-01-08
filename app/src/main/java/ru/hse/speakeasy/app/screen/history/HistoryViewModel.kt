package ru.hse.speakeasy.app.screen.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.hse.speakeasy.app.core.data.entity.TranslationHistoryEntity
import ru.hse.speakeasy.app.core.data.dao.TranslationHistoryDao
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao,
): ViewModel() {
    fun getHistory(): Flow<List<TranslationHistoryEntity>> {
        return translationHistoryDao.getTranslationHistory()
    }
}