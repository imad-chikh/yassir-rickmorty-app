package com.imad.yassir.rickmorty.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = RickGreen80,
    onPrimary = RickBlack,
    primaryContainer = RickGreen40,
    onPrimaryContainer = RickGreen80,

    secondary = PortalBlue80,
    onSecondary = RickBlack,
    secondaryContainer = PortalBlue40,
    onSecondaryContainer = PortalBlue80,

    tertiary = MortyYellow80,
    onTertiary = RickBlack,
    tertiaryContainer = MortyYellow40,
    onTertiaryContainer = MortyYellow80,

    error = ErrorRed,
    onError = RickWhite,
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = RickBlack,
    onBackground = RickGrey80,

    surface = Color(0xFF1A1A1A),
    onSurface = RickGrey80,
    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = RickGrey80,

    outline = RickGrey40,
    outlineVariant = Color(0xFF444444),

    inverseSurface = RickWhite,
    inverseOnSurface = RickBlack,
    inversePrimary = RickGreen40,

    surfaceTint = PortalGlow
)

private val LightColorScheme = lightColorScheme(
    primary = RickGreen40,
    onPrimary = RickWhite,
    primaryContainer = RickGreen80,
    onPrimaryContainer = RickGreen40,

    secondary = PortalBlue40,
    onSecondary = RickWhite,
    secondaryContainer = PortalBlue80,
    onSecondaryContainer = PortalBlue40,

    tertiary = MortyYellow40,
    onTertiary = RickWhite,
    tertiaryContainer = MortyYellow80,
    onTertiaryContainer = MortyYellow40,

    error = ErrorRed,
    onError = RickWhite,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    background = RickWhite,
    onBackground = RickBlack,

    surface = RickWhite,
    onSurface = RickBlack,
    surfaceVariant = RickGrey80,
    onSurfaceVariant = RickGrey40,

    outline = RickGrey40,
    outlineVariant = RickGrey80,

    inverseSurface = RickBlack,
    inverseOnSurface = RickWhite,
    inversePrimary = RickGreen80,

    surfaceTint = PortalGlow
)

@Composable
fun YassirRickMortyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Set to false to always use Rick and Morty colors
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}