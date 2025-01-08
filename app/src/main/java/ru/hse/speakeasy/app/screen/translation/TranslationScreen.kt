package ru.hse.speakeasy.app.screen.translation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.hse.speakeasy.app.ui.LanguageSelector
import ru.hse.speakeasy.app.ui.TextInput
import ru.hse.speakeasy.app.ui.TranslateButton
import ru.hse.speakeasy.app.ui.TranslationResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslationScreen(
    viewModel: TranslationViewModel = hiltViewModel(),
    scope: CoroutineScope,
) {
    val uiState = viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors().copy(containerColor = Color.White),
                title = { Text("Translation App") }
            )

            LanguageSelector(
                sourceLanguage = uiState.value.sourceLang,
                targetLanguage = uiState.value.targetLang,
                onSwapLanguages = { viewModel.swapLanguages() },
                onSelectSourceLanguage = { viewModel.selectSourceLanguage(it) },
                onSelectTargetLanguage = { viewModel.selectTargetLanguage(it) },
            )

            TextInput(
                language = uiState.value.sourceLang.title,
                text = uiState.value.inputText,
                onTextChange = { viewModel.updateInputText(it) },
                onClearText = { viewModel.clearInputText() },
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            TranslateButton(
                onTranslate = { viewModel.translateText() },
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            uiState.value.translatedText?.let {
                TranslationResult(
                    result = it,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    addToFavorite = {
                        viewModel.addToFavorite()
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Added to favorites",
                            )
                        }
                    }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp)
        )
    }

}