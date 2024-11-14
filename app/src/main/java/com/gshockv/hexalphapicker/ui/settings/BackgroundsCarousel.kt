package com.gshockv.hexalphapicker.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.hexalphapicker.R
import com.gshockv.hexalphapicker.ui.DisplaySurfaceBackgroundItem
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackgroundsCarousel(
  modifier: Modifier = Modifier,
  allDisplaySurfaceBgs: List<DisplaySurfaceBackgroundItem>,
  onSelectDisplaySurfaceBackground: (DisplaySurfaceBackgroundItem) -> Unit
) {
  val backgroundItems = remember { allDisplaySurfaceBgs }

  HorizontalMultiBrowseCarousel(
    modifier = modifier,
    state = rememberCarouselState { backgroundItems.count() },
    preferredItemWidth = 200.dp,
    itemSpacing = 8.dp,
    contentPadding = PaddingValues(horizontal = 8.dp)
  ) { indx ->
    val bg = backgroundItems[indx]
    // TODO: Highlight current item
    Box(
      modifier = Modifier.clickable { onSelectDisplaySurfaceBackground(bg) }
    ) {
      Image(
        modifier = Modifier
          .height(205.dp)
          .maskClip(MaterialTheme.shapes.extraLarge),
        painter = painterResource(id = bg.backgroundId),
        contentScale = ContentScale.Crop,
        contentDescription = ""
      )
      Text(
        text = "#${bg.id + 1}",
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .padding(end = 20.dp, bottom = 16.dp)
          .align(alignment = Alignment.BottomEnd)
          .background(
            color = Color.Red.copy(alpha = 0.45f),
            shape = CircleShape
          )
          .padding(horizontal = 8.dp, vertical = 2.dp)
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun PreviewBackgroundCarousel() {
  HexAlphaPickerTheme {
    BackgroundsCarousel(
      allDisplaySurfaceBgs = listOf(),
      onSelectDisplaySurfaceBackground = {})
  }
}
