package com.gshockv.hexalphapicker.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
  primary = PrimaryDark,
  secondary = PurpleGrey80,
  tertiary = Pink80,
  background = BackgroundDark,
  surface = BackgroundDark
)

private val LightColorScheme = lightColorScheme(
  primary = PrimaryLight,
  secondary = PurpleGrey40,
  tertiary = Pink40,
  background = BackgroundLight,
  surface = BackgroundLight
)

@Composable
fun HexAlphaPickerTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}