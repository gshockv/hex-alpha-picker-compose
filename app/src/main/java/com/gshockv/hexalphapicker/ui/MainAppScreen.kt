package com.gshockv.hexalphapicker.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme

@Composable
fun MainAppScreen() {
  HexAlphaPickerTheme {
    NavigationBasedLayout(modifier = Modifier.fillMaxSize())
  }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewMainScreenLightTheme() {
  HexAlphaPickerTheme {
    MainAppScreen()
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewMainScreenDarkTheme() {
  HexAlphaPickerTheme {
    MainAppScreen()
  }
}
