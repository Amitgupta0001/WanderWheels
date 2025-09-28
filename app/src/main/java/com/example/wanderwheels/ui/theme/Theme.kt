package com.example.wanderwheels.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006A61),
    secondary = Color(0xFF4A635F),
    tertiary = Color(0xFF3D6471),
    background = Color(0xFFFBFDFC),
    surface = Color(0xFFFBFDFC),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF191C1C),
    onSurface = Color(0xFF191C1C),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4FD9C9),
    secondary = Color(0xFFB1CCC7),
    tertiary = Color(0xFFA3C9D9),
    background = Color(0xFF191C1C),
    surface = Color(0xFF191C1C),
    onPrimary = Color(0xFF003732),
    onSecondary = Color(0xFF203532),
    onTertiary = Color(0xFF05313F),
    onBackground = Color(0xFFE0E3E2),
    onSurface = Color(0xFFE0E3E2),
)

@Composable
fun WanderWheelsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}