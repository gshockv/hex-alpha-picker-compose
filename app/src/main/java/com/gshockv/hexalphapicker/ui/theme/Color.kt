package com.gshockv.hexalphapicker.ui.theme

import androidx.compose.ui.graphics.Color

val BackgroundLight = Color(0xFFF9FAEF)
val BackgroundDark = Color(0xFF20251E)

val PrimaryLight = Color(0xFFE3E5C1)
val PrimaryDark = Color(0xFF46492F)

val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


fun appBarPrefixColor(darkTheme: Boolean) =
  if (darkTheme) Color(0xFFFF9D77) else Color(0xFFAF522D)

fun appBarSuffixColor(darkTheme: Boolean) =
  if (darkTheme) Color(0xFF5AE841) else Color(0xFF1D6910)

fun alphaSliderActiveTrackColor(darkTheme: Boolean) =
  if (darkTheme) Color(0xFF46492F) else Color(0xFFD2EA05)

fun alphaSliderThumbColor(darkTheme: Boolean) =
  if (darkTheme) Color(0xFF5AE841) else Color(0xFF1D6910)

fun overlayColorItemBorderColor(darkTheme: Boolean) =
  if (darkTheme) Color(0xFFFFFFFF) else Color(0xFFCCCFCC)

fun overlayColorItemCurrent() = Color(0xFF41CD0D)

fun settingsPaneSurfaceBorderColor(darkTheme: Boolean) =
  if (darkTheme) PrimaryDark else PrimaryLight
