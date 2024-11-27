package com.gshockv.hexalphapicker.ui.picker

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gshockv.hexalphapicker.R
import com.gshockv.hexalphapicker.ui.AlphaPickerUiState
import com.gshockv.hexalphapicker.ui.AlphaPickerViewModel
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme
import com.gshockv.hexalphapicker.ui.theme.alphaSliderActiveTrackColor
import com.gshockv.hexalphapicker.ui.theme.alphaSliderThumbColor
import com.gshockv.hexalphapicker.ui.theme.appBarSuffixColor
import kotlin.math.roundToInt

// Stateful component
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldScope.AlphaPickerPane(
  viewModel: AlphaPickerViewModel,
  onePaneMode: Boolean,
  modifier: Modifier = Modifier,
  onSettingsClick: () -> Unit = {}
) {
  val uiState by viewModel.uiState.collectAsState()

  AnimatedPane {
    AlphaPickerPane(
      modifier = modifier,
      uiState = uiState,
      onAlphaValueChange = { viewModel.slideRawPosition(it) },
      onSettingsClick = onSettingsClick,
      onePaneMode = onePaneMode
    )
  }
}

// Stateless component
@Composable
private fun AlphaPickerPane(
  uiState: AlphaPickerUiState,
  onAlphaValueChange: (Int) -> Unit,
  modifier: Modifier = Modifier,
  onSettingsClick: () -> Unit = {},
  onePaneMode: Boolean = false
) {
  Scaffold(
    topBar = {
      AlphaPickerTopBar(
        onSettingsClick = onSettingsClick,
        showSettingsButton = onePaneMode
      )
    }
  ) { innerPadding ->

    val horizontalPadding = if (onePaneMode) 8.dp else 120.dp
    val verticalPadding = if (onePaneMode) 8.dp else 32.dp

    Column(
      modifier = modifier
        .padding(innerPadding)
        .padding(
          horizontal = horizontalPadding,
          vertical = verticalPadding
        )
    ) {
      DisplayComponent(
        htmlColor = uiState.htmlBackgroundDisplayValue,
        overlayColorCode = uiState.overlayColor,
        displaySurfaceBackground = uiState.displaySurfaceBackground.backgroundId,
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.45f)
          .padding(top = 8.dp)
      )

      Box(
        modifier = Modifier
          .fillMaxHeight(1f)
          .padding(bottom = 32.dp)
      ) {
        Column(
          modifier = Modifier.align(alignment = Alignment.BottomCenter)
        ) {
          CurrentValueText(
            currentValue = uiState.rawPosition,
            modifier = Modifier.padding(bottom = 32.dp)
          )
          AlphaSlider(
            currentValue = uiState.rawPosition,
            onCurrentValueChange = onAlphaValueChange,
            modifier = Modifier.padding(all = 8.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun CurrentValueText(
  modifier: Modifier = Modifier,
  currentValue: Int = 0
) {
  Text(
    text = "$currentValue%",
    textAlign = TextAlign.Center,
    color = appBarSuffixColor(isSystemInDarkTheme()),
    fontSize = 70.sp,
    fontFamily = FontFamily(
      Font(R.font.righteous_regular)
    ),
    modifier = modifier.fillMaxWidth()
  )
}

@Composable
private fun AlphaSlider(
  modifier: Modifier = Modifier,
  currentValue: Int,
  onCurrentValueChange: (Int) -> Unit
) {
  Slider(
    modifier = modifier,
    value = currentValue.toFloat(),
    steps = 19,
    valueRange = 0f..100f,
    onValueChange = {
      onCurrentValueChange((it / 5).roundToInt() * 5)
    },
    colors = SliderDefaults.colors(
      activeTrackColor = alphaSliderActiveTrackColor(isSystemInDarkTheme()),
      thumbColor = alphaSliderThumbColor(isSystemInDarkTheme())
    )
  )
}

@Composable
@Preview(showBackground = true)
fun PreviewPaneInOnePaneMode() {
  HexAlphaPickerTheme {
    val viewModel = AlphaPickerViewModel()
    val uiState = viewModel.uiState.collectAsState()

    AlphaPickerPane(
      modifier = Modifier,
      uiState = uiState.value,
      onAlphaValueChange = { viewModel.slideRawPosition(it) },
      onSettingsClick = {},
      onePaneMode = true
    )
  }
}
