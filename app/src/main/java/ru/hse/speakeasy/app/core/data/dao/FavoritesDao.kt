package ru.hse.speakeasy.app.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.hse.speakeasy.app.core.data.entity.FavoriteEntity

@Dao
interface FavoritesDao {
    @Insert
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM favorite ORDER BY timestamp")
    fun getFavorites(): Flow<List<FavoriteEntity>>
}