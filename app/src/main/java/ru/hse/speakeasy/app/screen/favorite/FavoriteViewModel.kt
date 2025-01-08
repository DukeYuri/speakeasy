package ru.hse.speakeasy.app.screen.favorite

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.hse.speakeasy.app.core.data.dao.FavoritesDao
import ru.hse.speakeasy.app.core.data.entity.FavoriteEntity
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoritesDao: FavoritesDao,
): ViewModel() {
    fun getFavorites(): Flow<List<FavoriteEntity>> {
        return favoritesDao.getFavorites()
    }
}