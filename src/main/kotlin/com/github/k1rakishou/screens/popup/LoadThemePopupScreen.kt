package com.github.k1rakishou.screens.popup

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.k1rakishou.LocalDeps
import com.github.k1rakishou.LocalKurobaTheme
import com.github.k1rakishou.data.KurobaTheme
import com.github.k1rakishou.data.KurobaThemeJson
import com.google.gson.Gson


@Composable
fun LoadThemePopupScreen(
  popupScreen: PopupScreen.LoadTheme,
  showErrorPopup: (PopupScreen.Error) -> Unit,
) {
  val kurobaTheme = LocalKurobaTheme.current
  val deps = LocalDeps.current
  val gson = deps.gson

  Column(modifier = Modifier.fillMaxSize()) {
    var themeJson by remember { mutableStateOf("") }

    TextField(
      modifier = Modifier.fillMaxWidth().weight(1f),
      value = themeJson,
      textStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace
      ),
      maxLines = Int.MAX_VALUE,
      onValueChange = { newValue -> themeJson = newValue }
    )

    Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
      Spacer(modifier = Modifier.weight(1f))

      Button(
        onClick = {
          val success = parseThemeJsonAndApply(kurobaTheme, gson, themeJson, showErrorPopup)
          if (success) {
            deps.popPopupScreen(popupScreen)
          }
        }
      ) {
        Text("Apply")
      }

      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}

fun parseThemeJsonAndApply(
  kurobaTheme: KurobaTheme,
  gson: Gson,
  themeJson: String,
  showErrorPopup: (PopupScreen.Error) -> Unit
): Boolean {
  val kurobaThemeJson = try {
    gson.fromJson<KurobaThemeJson>(themeJson, KurobaThemeJson::class.java)
  } catch (error: Throwable) {
    showErrorPopup(PopupScreen.Error.Exception("Error trying to parse theme json", error))
    return false
  }

  if (kurobaThemeJson == null) {
    showErrorPopup(PopupScreen.Error.Message("Error trying to parse theme json", "KurobaThemeJson is null"))
    return false
  }

  try {
    kurobaTheme.fromKurobaThemeJson(kurobaThemeJson)
  } catch (error: Throwable) {
    showErrorPopup(PopupScreen.Error.Message("Error trying to parse theme json", error.message ?: "Unknown error"))
    return false
  }

  return true
}
