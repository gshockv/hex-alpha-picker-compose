@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.gshockv.hexalphapicker.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gshockv.hexalphapicker.ui.picker.AlphaPickerPane
import com.gshockv.hexalphapicker.ui.settings.SettingsPane

@Composable
fun AdaptivePanesLayout() {
  val alphaPickerViewModel: AlphaPickerViewModel = hiltViewModel()
  val navigator = rememberSupportingPaneScaffoldNavigator()

  BackHandler(navigator.canNavigateBack()) {
    navigator.navigateBack()
  }

  val onePaneLayout =
    navigator.scaffoldValue[SupportingPaneScaffoldRole.Main] == PaneAdaptedValue.Hidden ||
        navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden

  SupportingPaneScaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.surface),
    directive = navigator.scaffoldDirective,
    value = navigator.scaffoldValue,

    mainPane = {
      AlphaPickerPane(
        viewModel = alphaPickerViewModel,
        onePaneMode = onePaneLayout,
        onSettingsClick = {
          navigator.navigateTo(SupportingPaneScaffoldRole.Supporting)
        }
      )
    },
    supportingPane = {
      SettingsPane(
        viewModel = alphaPickerViewModel,
        onePaneMode = onePaneLayout,
        onBackClick = {
          navigator.navigateBack()
        }
      )
    }
  )
}
