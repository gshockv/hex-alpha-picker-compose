package com.gshockv.hexalphapicker.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.gshockv.hexalphapicker.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.roundToInt

const val LOG_TAG = "AlphaPickerViewModel"

const val INITIAL_OVERLAY_COLOR_INDEX = 2
const val INITIAL_DISPLAY_SURFACE_BG_INDEX = 4
const val INITIAL_ALPHA = 85

data class DisplaySurfaceBackgroundItem(
  val id: Int,
  @DrawableRes val backgroundId: Int
)

data class OverlayColorItem(
  val id: Int,
  val colorInHex: String
)

data class AlphaPickerUiState(
  val rawPosition: Int = INITIAL_ALPHA,
  val htmlBackgroundDisplayValue: String = "",
  val overlayColor: Long = 0xFF,
  val overlayBaseColor: OverlayColorItem,
  val displaySurfaceBackground: DisplaySurfaceBackgroundItem,
)

@HiltViewModel
class AlphaPickerViewModel @Inject constructor() : ViewModel() {
  private val initialOverlayBaseColor = overlayColors()[INITIAL_OVERLAY_COLOR_INDEX]
  private val initialDisplaySurfaceBg =
    displaySurfaceBackgrounds()[INITIAL_DISPLAY_SURFACE_BG_INDEX]

  private val _uiState = MutableStateFlow(
    AlphaPickerUiState(
      htmlBackgroundDisplayValue = calculateAlphaHtmlValue(
        position = INITIAL_ALPHA,
        initialBaseColor = initialOverlayBaseColor.colorInHex
      ),
      overlayColor = calculateOverlayColor(
        position = INITIAL_ALPHA,
        initialBaseColor = initialOverlayBaseColor.colorInHex
      ),
      overlayBaseColor = initialOverlayBaseColor,
      displaySurfaceBackground = initialDisplaySurfaceBg
    )
  )
  val uiState: StateFlow<AlphaPickerUiState> = _uiState.asStateFlow()

  fun slideRawPosition(position: Int) {
    _uiState.update { currentState ->
      currentState.copy(
        rawPosition = position,
        htmlBackgroundDisplayValue = calculateAlphaHtmlValue(position),
        overlayColor = calculateOverlayColor(position)
      )
    }
  }

  fun changeOverlayColor(overlayColor: OverlayColorItem) {
    _uiState.update { currentState ->
      currentState.copy(
        overlayBaseColor = overlayColor
      )
    }
    Log.d(LOG_TAG, "Applying overlay color: $overlayColor")
  }

  fun changeDisplaySurfaceBackground(displaySurfaceBg: DisplaySurfaceBackgroundItem) {
    _uiState.update { currentState ->
      currentState.copy(
        displaySurfaceBackground = displaySurfaceBg
      )
    }
    Log.d(LOG_TAG, "Applying surface background: $displaySurfaceBg")
  }

  fun displaySurfaceBackgrounds() = listOf(
    DisplaySurfaceBackgroundItem(0, R.drawable.bg_1),
    DisplaySurfaceBackgroundItem(1, R.drawable.bg_2),
    DisplaySurfaceBackgroundItem(2, R.drawable.bg_3),
    DisplaySurfaceBackgroundItem(3, R.drawable.bg_4),
    DisplaySurfaceBackgroundItem(4, R.drawable.bg_5),
    DisplaySurfaceBackgroundItem(5, R.drawable.bg_6),
    DisplaySurfaceBackgroundItem(6, R.drawable.bg_7),
    DisplaySurfaceBackgroundItem(7, R.drawable.bg_8),
    DisplaySurfaceBackgroundItem(8, R.drawable.bg_9),
    DisplaySurfaceBackgroundItem(9, R.drawable.bg_10)
  )

  fun overlayColors() = listOf(
    OverlayColorItem(0, "3073BB"),
    OverlayColorItem(1, "228A20"),
    OverlayColorItem(2, "AA3A3A"),
    OverlayColorItem(3, "D7DB05"),
    OverlayColorItem(4, "695620"),
    OverlayColorItem(5, "383434"),
  )

  /**
   * Converts given slider position from float value to HTML representation
   */
  private fun calculateAlphaHtmlValue(position: Int, initialBaseColor: String? = null): String {
    return "#" + calculateAlpha(position) + (initialBaseColor
      ?: _uiState.value.overlayBaseColor.colorInHex)
  }

  /**
   * Converts given slider position from float value to string representation
   * need using HEX format
   */
  private fun calculateOverlayColor(position: Int, initialBaseColor: String? = null): Long {
    return (calculateAlpha(position) + (initialBaseColor
      ?: _uiState.value.overlayBaseColor.colorInHex)).toLong(16)
  }

  /**
   * Converts raw position to HEX alpha representation
   */
  private fun calculateAlpha(position: Int): String {
    val limitedPercentage = position.coerceIn(0, 100)
    val alpha = (limitedPercentage / 100.0 * 255).roundToInt()
    val hexAlpha = alpha.toString(16).padStart(2, '0')
    return hexAlpha
  }
}
