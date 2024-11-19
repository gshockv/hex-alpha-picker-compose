package com.gshockv.hexalphapicker.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gshockv.hexalphapicker.ui.picker.AlphaPicker
import com.gshockv.hexalphapicker.ui.settings.PickerSettings

@Composable
fun NavigationBasedLayout(
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()
  val alphaPickerViewModel: AlphaPickerViewModel = hiltViewModel()

  NavHost(
    navController = navController,
    startDestination = AlphaPickerRoute,
    modifier = modifier
  ) {
    composable<AlphaPickerRoute> {
      AlphaPicker(
        viewModel = alphaPickerViewModel,
        onSettingsClick = { navController.navigate(route = SettingsRoute) },
        modifier = Modifier.fillMaxSize()
      )
    }
    composable<SettingsRoute> {
      PickerSettings(
        viewModel = alphaPickerViewModel,
        onBackClick = { navController.popBackStack() },
        modifier = Modifier.fillMaxSize()
      )
    }
  }
}
