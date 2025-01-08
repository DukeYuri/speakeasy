package ru.hse.speakeasy.app.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.hse.speakeasy.app.core.data.entity.TranslationHistoryEntity

@Dao
interface TranslationHistoryDao {
    @Insert
    suspend fun insertHistory(history: TranslationHistoryEntity)

    @Query("SELECT * FROM translation_history ORDER BY timestamp")
    fun getTranslationHistory(): Flow<List<TranslationHistoryEntity>>
}