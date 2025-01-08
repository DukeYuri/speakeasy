package ru.hse.speakeasy.app.core.domain.favorite

import ru.hse.speakeasy.app.core.data.dao.FavoritesDao
import ru.hse.speakeasy.app.core.data.entity.FavoriteEntity
import javax.inject.Inject

class SaveFavoriteUseCase @Inject constructor(
    private val favoriteDao: FavoritesDao,
) {
    suspend fun save(sourceText: String, translatedText: String) {
        favoriteDao.insertFavorite(
            FavoriteEntity(
                sourceText = sourceText,
                translatedText = translatedText
            )
        )
    }
}