package com.gshockv.hexalphapicker.ui.picker

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gshockv.hexalphapicker.R

@Composable
fun DisplayComponent(
  modifier: Modifier = Modifier,
  htmlColor: String = "html color",
  overlayColorCode: Long = 0xFF,
  @DrawableRes displaySurfaceBackground: Int
) {
  Card(
    modifier = modifier.padding(8.dp)
  ) {
    Box(
    ) {
      Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = displaySurfaceBackground),
        contentScale = ContentScale.Crop,
        contentDescription = ""
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(10.dp)
          .background(
            color = Color(overlayColorCode),
            shape = RoundedCornerShape(8.dp)
          )
      ) {
        Text(
          text = htmlColor.uppercase(),
          modifier = Modifier.align(alignment = Alignment.Center),
          textAlign = TextAlign.Center,
          color = Color.White,
          fontSize = 40.sp,
          fontWeight = FontWeight.Bold,
          fontFamily = FontFamily.Monospace,
          letterSpacing = 4.sp
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayComponent() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(320.dp)
  ) {
    DisplayComponent(displaySurfaceBackground = R.drawable.bg_8)
  }
}
