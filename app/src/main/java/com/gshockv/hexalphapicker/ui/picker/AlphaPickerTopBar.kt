package com.gshockv.hexalphapicker.ui.picker

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gshockv.hexalphapicker.R
import com.gshockv.hexalphapicker.ui.theme.HexAlphaPickerTheme
import com.gshockv.hexalphapicker.ui.theme.appBarPrefixColor
import com.gshockv.hexalphapicker.ui.theme.appBarSuffixColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphaPickerTopBar(
  onSettingsClick: () -> Unit,
  showSettingsButton: Boolean = false
) {
  TopAppBar(title = {
    Row {
      Text(
        text = "HEX",
        color = appBarPrefixColor(isSystemInDarkTheme()),
        fontSize = 24.sp,
        fontFamily = FontFamily(
          Font(R.font.rock_n_roll_one)
        )
      )
      Text(
        text = "AlphaPicker",
        color = appBarSuffixColor(isSystemInDarkTheme()),
        fontSize = 24.sp,
        fontFamily = FontFamily(
          Font(R.font.rock_n_roll_one)
        ),
        modifier = Modifier.padding(start = 8.dp)
      )
    }
  }, colors = TopAppBarDefaults.topAppBarColors(
    containerColor = Color.Transparent,
  ), actions = {
    if (showSettingsButton) {
      IconButton(onClick = onSettingsClick) {
        Icon(
          imageVector = Icons.Outlined.Settings, contentDescription = "Settings"
        )
      }
    }
  })
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewAppBarLightTheme() {
  HexAlphaPickerTheme {
    AlphaPickerTopBar(onSettingsClick = {})
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewAppBarDarkTheme() {
  HexAlphaPickerTheme {
    AlphaPickerTopBar(onSettingsClick = {})
  }
}
