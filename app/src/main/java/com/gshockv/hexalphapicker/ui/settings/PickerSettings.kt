package com.gshockv.hexalphapicker.ui.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gshockv.hexalphapicker.R
import com.gshockv.hexalphapicker.ui.AlphaPickerViewModel
import com.gshockv.hexalphapicker.ui.DisplaySurfaceBackgroundItem
import com.gshockv.hexalphapicker.ui.OverlayColorItem
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme

// Stateful
@Composable
fun PickerSettings(
  modifier: Modifier = Modifier,
  viewModel: AlphaPickerViewModel = hiltViewModel(),
  onBackClick: () -> Unit
) {
  val uiState = viewModel.uiState.collectAsState()

  PickerSettings(
    modifier = modifier,
    displaySurfaceBackgrounds = viewModel.displaySurfaceBackgrounds(),
    overlayColors = viewModel.overlayColors(),
    onSelectOverlayColor = { color -> viewModel.changeOverlayColor(color) },
    onSelectDisplaySurfaceBackground = { bg -> viewModel.changeDisplaySurfaceBackground(bg) },
    onApplyClick = onBackClick,
    onBackClick = onBackClick
  )
}

// Stateless
@Composable
private fun PickerSettings(
  modifier: Modifier = Modifier,
  displaySurfaceBackgrounds: List<DisplaySurfaceBackgroundItem>,
  overlayColors: List<OverlayColorItem>,
  onApplyClick: () -> Unit,
  onBackClick: () -> Unit,
  onSelectDisplaySurfaceBackground: (DisplaySurfaceBackgroundItem) -> Unit,
  onSelectOverlayColor: (OverlayColorItem) -> Unit
) {
  Scaffold(
    topBar = {
      SettingsAppBar(
        onApplyClick = onApplyClick,
        onBackClick = onBackClick
      )
    },
    modifier = modifier
  ) { innerPadding ->
    Column(
      modifier = Modifier.padding(innerPadding)
    ) {
      Text(
        text = stringResource(id = R.string.settings_backgrounds),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
      )
      BackgroundsCarousel(
        allDisplaySurfaceBgs = displaySurfaceBackgrounds,
        onSelectDisplaySurfaceBackground = onSelectDisplaySurfaceBackground
      )
      Text(
        text = stringResource(id = R.string.settings_colors),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
      )
      ColorsGrid(
        modifier = Modifier.padding(horizontal = 16.dp),
        overlayColors = overlayColors,
        onSelectOverlayColor = onSelectOverlayColor
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
  onApplyClick: () -> Unit,
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
    },
    actions = {
      IconButton(
        onClick = onApplyClick
      ) {
        Icon(
          imageVector = Icons.Filled.Check,
          contentDescription = ""
        )
      }
    }
  )
}

@Composable
@Preview(showSystemUi = true)
fun PreviewSettingsScreen() {
  HexAlphaPickerTheme {
    PickerSettings(
      onBackClick = {}
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewSettingsScreenDarkTheme() {
  HexAlphaPickerTheme {
    PickerSettings(
      onBackClick = {}
    )
  }
}
