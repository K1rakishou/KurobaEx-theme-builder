package com.github.k1rakishou.screens.popup

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class PopupScreen(
  val title: String,
  val maxWidth: Dp = 900.dp,
  val maxHeight: Dp = 700.dp
  ) {
  object LoadTheme : PopupScreen("Load theme")
  object SaveTheme : PopupScreen("Save theme")

  sealed class Error(
    title: String,
    maxWidth: Dp = 900.dp,
    maxHeight: Dp = 700.dp
  ) : PopupScreen(
    title = title,
    maxWidth = maxWidth,
    maxHeight = maxHeight
  ) {
    class Message(
      title: String,
      val errorMessage: String
    ) : Error(title = title, maxWidth = 600.dp, maxHeight = 400.dp)

    class Exception(
      title: String,
      val error: Throwable
    )  : Error(title = title)
  }
}