package com.gshockv.hexalphapicker.ui.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.hexalphapicker.R
import com.gshockv.hexalphapicker.ui.AlphaPickerViewModel
import com.gshockv.hexalphapicker.ui.DisplaySurfaceBackgroundItem
import com.gshockv.hexalphapicker.ui.OverlayColorItem
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme
import com.gshockv.hexalphapicker.ui.theme.settingsPaneSurfaceBorderColor

// Stateful
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldScope.SettingsPane(
  viewModel: AlphaPickerViewModel,
  onePaneMode: Boolean,
  onBackClick: () -> Unit = {}
) {
  val uiState = viewModel.uiState.collectAsState()

  AnimatedPane {
    if (onePaneMode) {
      SettingsPane(
        onePaneMode = true,
        displaySurfaceBackgrounds = viewModel.displaySurfaceBackgrounds(),
        overlayColors = viewModel.overlayColors(),
        currentSurfaceBackground = uiState.value.displaySurfaceBackground,
        currentOverlayColor = uiState.value.overlayBaseColor,
        onSelectOverlayColor = { color -> viewModel.changeOverlayColor(color) },
        onSelectDisplaySurfaceBackground = { bg -> viewModel.changeDisplaySurfaceBackground(bg) },
        onBackClick = onBackClick
      )
    } else {
      SettingsPane(
        onePaneMode = false,
        displaySurfaceBackgrounds = viewModel.displaySurfaceBackgrounds(),
        overlayColors = viewModel.overlayColors(),
        currentSurfaceBackground = uiState.value.displaySurfaceBackground,
        currentOverlayColor = uiState.value.overlayBaseColor,
        onSelectOverlayColor = { color -> viewModel.changeOverlayColor(color) },
        onSelectDisplaySurfaceBackground = { bg -> viewModel.changeDisplaySurfaceBackground(bg) },
        onBackClick = onBackClick,
        modifier = Modifier
          .padding(vertical = 60.dp, horizontal = 24.dp)
          .border(
            width = 2.dp,
            color = settingsPaneSurfaceBorderColor(isSystemInDarkTheme()),
            shape = RoundedCornerShape(20.dp)
          )
      )
    }
  }
}

// Stateless
@Composable
private fun SettingsPane(
  onePaneMode: Boolean,
  displaySurfaceBackgrounds: List<DisplaySurfaceBackgroundItem>,
  overlayColors: List<OverlayColorItem>,
  currentOverlayColor: OverlayColorItem,
  currentSurfaceBackground: DisplaySurfaceBackgroundItem,
  onBackClick: () -> Unit,
  onSelectDisplaySurfaceBackground: (DisplaySurfaceBackgroundItem) -> Unit,
  onSelectOverlayColor: (OverlayColorItem) -> Unit,
  modifier: Modifier = Modifier
) {
  Scaffold(
    topBar = {
      if (onePaneMode) {
        SettingsAppBar(
          onBackClick = onBackClick
        )
      }
    }
  ) { innerPadding ->
    Column(
      modifier = modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Text(
        text = stringResource(id = R.string.settings_backgrounds),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
      )
      BackgroundsCarousel(
        allDisplaySurfaceBgs = displaySurfaceBackgrounds,
        currentDisplaySurfaceBg = currentSurfaceBackground,
        onSelectDisplaySurfaceBackground = onSelectDisplaySurfaceBackground,
        modifier = Modifier.fillMaxWidth()
      )
      Text(
        text = stringResource(id = R.string.settings_colors),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
      )
      ColorsGrid(
        onePaneMode = onePaneMode,
        modifier = Modifier.padding(horizontal = 16.dp),
        overlayColors = overlayColors,
        onSelectOverlayColor = onSelectOverlayColor,
        currentOverlayColor = currentOverlayColor
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
  onBackClick: () -> Unit
) {
  TopAppBar(
    title = {
      Text(text = stringResource(R.string.settings_title))
    },
    navigationIcon = {
      IconButton(
        onClick = onBackClick
      ) {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.ArrowBack,
          contentDescription = ""
        )
      }
    }
  )
}

@Composable
@Preview(showBackground = true)
fun PreviewSettingsOnePaneMode() {
  val viewModel = AlphaPickerViewModel()
  val uiState = viewModel.uiState.collectAsState()

  HexAlphaPickerTheme {
    SettingsPane(
      onePaneMode = true,
      displaySurfaceBackgrounds = viewModel.displaySurfaceBackgrounds(),
      overlayColors = viewModel.overlayColors(),
      currentSurfaceBackground = uiState.value.displaySurfaceBackground,
      currentOverlayColor = uiState.value.overlayBaseColor,
      onSelectOverlayColor = { color -> viewModel.changeOverlayColor(color) },
      onSelectDisplaySurfaceBackground = { bg -> viewModel.changeDisplaySurfaceBackground(bg) },
      onBackClick = {}
    )
  }
}

