package ru.hse.speakeasy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.speakeasy.app.screen.history.HistoryScreen
import ru.hse.speakeasy.app.screen.translation.TranslationScreen
import ru.hse.speakeasy.app.ui.theme.SpeakEasyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeakEasyTheme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "translate"
            ) {
//                composable("chat") {}
//                composable("camera") {}
                composable("translate") { TranslationScreen() }
                composable("history") { HistoryScreen() }
//                composable("favorite") {}
            }
        }
    }

    //private val Destinations = listOf("chat", "camera", "translate", "history", "favorite")
    private val Destinations = listOf("translate", "history")

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        var selectedItem by rememberSaveable { mutableIntStateOf(2) }
//        val icons = listOf(
//            ImageVector.vectorResource(R.drawable.ic_chat),
//            ImageVector.vectorResource(R.drawable.ic_camera),
//            ImageVector.vectorResource(R.drawable.ic_translate),
//            ImageVector.vectorResource(R.drawable.ic_history),
//            ImageVector.vectorResource(R.drawable.ic_fav),
//        )
        val icons = listOf(
            ImageVector.vectorResource(R.drawable.ic_translate),
            ImageVector.vectorResource(R.drawable.ic_history),
        )


        NavigationBar(
            content = {
                Destinations.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = item
                            )
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(item)
                        }
                    )
                }
            }
        )
    }

    @Composable
    fun RowScope.BottomNavigationItem(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        alwaysShowLabel: Boolean = true,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) {
        NavigationBarItem(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            alwaysShowLabel = alwaysShowLabel,
            icon = if (selected) selectedIcon else icon,
            label = label,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
                unselectedIconColor = NavigationDefaults.navigationContentColor(),
                selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
                unselectedTextColor = NavigationDefaults.navigationContentColor(),
                indicatorColor = NavigationDefaults.navigationIndicatorColor(),
            )
        )
    }

    object NavigationDefaults {
        @Composable
        fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

        @Composable
        fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

        @Composable
        fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
    }

}