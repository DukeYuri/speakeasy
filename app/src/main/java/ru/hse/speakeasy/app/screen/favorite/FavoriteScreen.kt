package ru.hse.speakeasy.app.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.hse.speakeasy.app.core.data.entity.FavoriteEntity
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteList = viewModel.getFavorites().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.White),
            title = { Text("Favorites") }
        )

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(favoriteList.value) { favorite ->
                FavoriteItem(favorite)
            }
        }
    }
}

@Composable
fun FavoriteItem(history: FavoriteEntity) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Source: ${history.sourceText}")
        Text(text = "Translation: ${history.translatedText}")
        Text(text = "Timestamp: ${SimpleDateFormat().format(history.timestamp)}")
    }
}