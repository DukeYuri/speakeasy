package ru.hse.speakeasy.app.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationHistoryDao {
    @Insert
    suspend fun insertHistory(history: TranslationHistory)

    @Query("SELECT * FROM translation_history ORDER BY timestamp")
    fun getTranslationHistory(): Flow<List<TranslationHistory>>
}