package com.gshockv.hexalphapicker.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gshockv.hexalphapicker.ui.picker.AlphaPicker
import com.gshockv.hexalphapicker.ui.settings.PickerSettings
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme

@Composable
fun MainAppScreen() {
  HexAlphaPickerTheme {
    MainNavigation(modifier = Modifier.fillMaxSize())
  }
}

@Composable
fun MainNavigation(
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()
  NavHost(
    navController  = navController,
    startDestination = "alpha_picker",
    modifier = modifier
  ) {
    composable(
      route = "alpha_picker"
    ) {
      AlphaPicker(
        onSettingsClick = { navController.navigate("settings") },
        modifier = Modifier.fillMaxSize()
      )
    }
    composable(
      route = "settings"
    ) {
      PickerSettings(
        onBackClick = { navController.popBackStack() },
        modifier = Modifier.fillMaxSize())
    }
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
