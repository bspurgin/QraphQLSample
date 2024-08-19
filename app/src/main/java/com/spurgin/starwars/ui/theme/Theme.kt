package com.spurgin.starwars.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White,
    background = Color(0xFF1E1266),
    surface = Color(0xFF1E1266)
)

@Composable
fun StarWarsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography
    ) {
        GradientBackground {
            content()
        }
    }
}

@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        TopGradient,
                        BottomGradient
                    )
                )
            )
    ) {
        content()
    }
}

