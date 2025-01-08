package ru.hse.speakeasy.app.core.domain

import androidx.annotation.DrawableRes
import ru.hse.speakeasy.app.R

enum class LanguageCode(val code: String, @DrawableRes val flagIconRes: Int, val title: String) {
    ENGLISH("en", R.drawable.ic_flag_english, "English"),
    RUSSIAN("ru", R.drawable.ic_flag_russian, "Russian"),
}