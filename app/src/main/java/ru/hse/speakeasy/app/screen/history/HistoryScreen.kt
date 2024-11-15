package ru.hse.speakeasy.app.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.hse.speakeasy.app.core.data.TranslationHistory
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val historyList = viewModel.getHistory().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("History") })

        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(historyList.value) { history ->
                HistoryItem(history)
            }
        }
    }
}

@Composable
fun HistoryItem(history: TranslationHistory) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "Source: ${history.sourceText}")
        Text(text = "Translation: ${history.translatedText}")
        Text(text = "Timestamp: ${SimpleDateFormat().format(history.timestamp)}")
    }
}