package com.gshockv.hexalphapicker.com.gshockv.hexalphapicker.ui.picker

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gshockv.hexalphapicker.ui.AlphaPickerViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.gshockv.hexalphapicker.ui.DEFAULT_BG_COLOR
import com.gshockv.hexalphapicker.ui.INITIAL_ALPHA

@RunWith(AndroidJUnit4::class)
class AlphaPickerViewModelTest {
  private val hexColors = mapOf(
    100 to "FF",
    95 to "F2",
    90 to "E6",
    85 to "D9",
    80 to "CC",
    75 to "BF",
    70 to "B3",
    65 to "A6",
    60 to "99",
    55 to "8C",
    50 to "80",
    45 to "73",
    40 to "66",
    35 to "59",
    30 to "4D",
    25 to "40",
    20 to "33",
    15 to "26",
    10 to "1A",
    5 to "0D",
    0 to "00"
  )

  @Test
  fun testInitialize() = runTest {
    val viewModel = AlphaPickerViewModel()
    viewModel.uiState.test {
      assertThat(viewModel.uiState.value.rawPosition).isEqualTo(INITIAL_ALPHA)
      cancelAndIgnoreRemainingEvents()
    }
  }

  @Test
  fun testConvertingFromRawToBackground() = runTest {
    val viewModel = AlphaPickerViewModel()

    hexColors.keys.forEach { positionKey ->
      viewModel.slideRawPosition(positionKey)

      viewModel.uiState.test {
        assertThat(viewModel.uiState.value.rawPosition).isEqualTo(positionKey)
        assertThat(viewModel.uiState.value.htmlBackgroundDisplayValue.lowercase())
          .isEqualTo(expectedHtmlBackground(positionKey).lowercase())
        assertThat(viewModel.uiState.value.backgroundColor)
          .isEqualTo(expectedColorCode(positionKey))
        cancelAndIgnoreRemainingEvents()
      }
    }
  }

  private fun expectedHtmlBackground(position: Int) =
    "#${hexColors[position]}$DEFAULT_BG_COLOR"

  private fun expectedColorCode(position: Int) =
    ("${hexColors[position]}$DEFAULT_BG_COLOR").toLong(16)
}
