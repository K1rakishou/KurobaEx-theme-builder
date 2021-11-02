package screens

import LocalKurobaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BookmarksScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val backColor by kurobaTheme.backColor

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(backColor)
  ) {
    Text(modifier = Modifier.wrapContentSize().align(Alignment.Center), text = "Bookmarks", color = Color.White)
  }
}