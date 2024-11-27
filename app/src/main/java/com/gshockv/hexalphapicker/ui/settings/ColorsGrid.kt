package com.gshockv.hexalphapicker.ui.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.hexalphapicker.ui.OverlayColorItem
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme
import com.gshockv.hexalphapicker.ui.theme.overlayColorItemBorderColor
import com.gshockv.hexalphapicker.ui.theme.overlayColorItemCurrent

@Composable
fun ColorsGrid(
  onePaneMode: Boolean,
  modifier: Modifier = Modifier,
  overlayColors: List<OverlayColorItem>,
  currentOverlayColor: OverlayColorItem,
  onSelectOverlayColor: (OverlayColorItem) -> Unit
) {
  LazyVerticalGrid(
    modifier = modifier,
    columns = GridCells.Fixed(
      count = 3
    ),
    verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(overlayColors) { item ->
      ColorItemComponent(
        colorItem = item,
        onSelectOverlayColor = onSelectOverlayColor,
        isCurrent = item == currentOverlayColor,
        modifier = Modifier.size(
          size = if (onePaneMode) 120.dp else 60.dp
        )
      )
    }
  }
}

@Composable
fun ColorItemComponent(
  colorItem: OverlayColorItem,
  onSelectOverlayColor: (OverlayColorItem) -> Unit,
  modifier: Modifier = Modifier,
  isCurrent: Boolean = false
) {
  Surface(
    modifier = modifier
      .border(
        width = if (isCurrent) 4.dp else 2.dp,
        color = if (isCurrent) overlayColorItemCurrent() else overlayColorItemBorderColor(isSystemInDarkTheme()),
        shape = RoundedCornerShape(size = 10.dp)
      )
      .clip(shape = RoundedCornerShape(size = 10.dp))
      .clickable { onSelectOverlayColor(colorItem) },
    color = Color(android.graphics.Color.parseColor("#${colorItem.colorInHex}"))
  ) {
    // . . .
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewColorsGrid() {
  HexAlphaPickerTheme {
    ColorsGrid(
      overlayColors = listOf(OverlayColorItem(2, "AA3A3A")),
      currentOverlayColor = OverlayColorItem(2, "AA3A3A"),
      onSelectOverlayColor = {},
      onePaneMode = true
    )
  }
}
