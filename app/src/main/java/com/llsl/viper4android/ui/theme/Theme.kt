package com.llsl.viper4android.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LightColorScheme =
    lightColorScheme(
        primary = md_theme_light_primary,
        onPrimary = md_theme_light_onPrimary,
        primaryContainer = md_theme_light_primaryContainer,
        onPrimaryContainer = md_theme_light_onPrimaryContainer,
        secondary = md_theme_light_secondary,
        onSecondary = md_theme_light_onSecondary,
        secondaryContainer = md_theme_light_secondaryContainer,
        onSecondaryContainer = md_theme_light_onSecondaryContainer,
        tertiary = md_theme_light_tertiary,
        onTertiary = md_theme_light_onTertiary,
        tertiaryContainer = md_theme_light_tertiaryContainer,
        onTertiaryContainer = md_theme_light_onTertiaryContainer,
        error = md_theme_light_error,
        onError = md_theme_light_onError,
        errorContainer = md_theme_light_errorContainer,
        onErrorContainer = md_theme_light_onErrorContainer,
        background = md_theme_light_background,
        onBackground = md_theme_light_onBackground,
        surface = md_theme_light_surface,
        onSurface = md_theme_light_onSurface,
        surfaceVariant = md_theme_light_surfaceVariant,
        onSurfaceVariant = md_theme_light_onSurfaceVariant,
        outline = md_theme_light_outline,
        outlineVariant = md_theme_light_outlineVariant,
        surfaceContainerLowest = md_theme_light_surfaceContainerLowest,
        surfaceContainerLow = md_theme_light_surfaceContainerLow,
        surfaceContainer = md_theme_light_surfaceContainer,
        surfaceContainerHigh = md_theme_light_surfaceContainerHigh,
        surfaceContainerHighest = md_theme_light_surfaceContainerHighest,
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = md_theme_dark_primary,
        onPrimary = md_theme_dark_onPrimary,
        primaryContainer = md_theme_dark_primaryContainer,
        onPrimaryContainer = md_theme_dark_onPrimaryContainer,
        secondary = md_theme_dark_secondary,
        onSecondary = md_theme_dark_onSecondary,
        secondaryContainer = md_theme_dark_secondaryContainer,
        onSecondaryContainer = md_theme_dark_onSecondaryContainer,
        tertiary = md_theme_dark_tertiary,
        onTertiary = md_theme_dark_onTertiary,
        tertiaryContainer = md_theme_dark_tertiaryContainer,
        onTertiaryContainer = md_theme_dark_onTertiaryContainer,
        error = md_theme_dark_error,
        onError = md_theme_dark_onError,
        errorContainer = md_theme_dark_errorContainer,
        onErrorContainer = md_theme_dark_onErrorContainer,
        background = md_theme_dark_background,
        onBackground = md_theme_dark_onBackground,
        surface = md_theme_dark_surface,
        onSurface = md_theme_dark_onSurface,
        surfaceVariant = md_theme_dark_surfaceVariant,
        onSurfaceVariant = md_theme_dark_onSurfaceVariant,
        outline = md_theme_dark_outline,
        outlineVariant = md_theme_dark_outlineVariant,
        surfaceContainerLowest = md_theme_dark_surfaceContainerLowest,
        surfaceContainerLow = md_theme_dark_surfaceContainerLow,
        surfaceContainer = md_theme_dark_surfaceContainer,
        surfaceContainerHigh = md_theme_dark_surfaceContainerHigh,
        surfaceContainerHighest = md_theme_dark_surfaceContainerHighest,
    )

// sesl_rounded_corner_radius / sesl_dialog_background_corner_radius = 26dp,
// sesl_button_corner_radius = 18dp.
private val OneUiShapes =
    Shapes(
        extraSmall = RoundedCornerShape(8.dp),
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(18.dp),
        large = RoundedCornerShape(26.dp),
        extraLarge = RoundedCornerShape(26.dp),
    )

// One UI Sans is not available to third-party apps, so this matches its sizes and weights
// (list title 17sp, summary 14sp, dialog button 17sp, extended app bar title 34sp) on the system font.
private val OneUiTypography =
    Typography().run {
        copy(
            headlineMedium = headlineMedium.copy(fontSize = 34.sp, lineHeight = 38.sp, fontWeight = FontWeight.Normal),
            headlineSmall = headlineSmall.copy(fontSize = 24.sp, lineHeight = 30.sp, fontWeight = FontWeight.Medium),
            titleLarge = titleLarge.copy(fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.Medium),
            titleMedium = titleMedium.copy(fontSize = 19.sp, lineHeight = 25.sp, fontWeight = FontWeight.Medium),
            titleSmall = titleSmall.copy(fontSize = 17.sp, lineHeight = 22.sp, fontWeight = FontWeight.Medium),
            bodyLarge = bodyLarge.copy(fontSize = 17.sp, lineHeight = 23.sp),
            bodyMedium = bodyMedium.copy(fontSize = 16.sp, lineHeight = 22.sp),
            bodySmall = bodySmall.copy(fontSize = 14.sp, lineHeight = 19.sp),
            labelLarge = labelLarge.copy(fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium),
            labelMedium = labelMedium.copy(fontSize = 13.sp, lineHeight = 17.sp, fontWeight = FontWeight.Medium),
            labelSmall = labelSmall.copy(fontSize = 12.sp, lineHeight = 16.sp, fontWeight = FontWeight.Medium),
        )
    }

@Composable
fun ViperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // One UI uses its own fixed palette, so Material You wallpaper colours are off by default.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> {
                DarkColorScheme
            }

            else -> {
                LightColorScheme
            }
        }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = OneUiShapes,
        typography = OneUiTypography,
        content = content,
    )
}
