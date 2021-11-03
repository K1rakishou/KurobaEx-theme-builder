package com.github.k1rakishou.screens.popup

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.k1rakishou.LocalDeps

@Composable
fun ErrorMessagePopupScreen(errorScreen: PopupScreen.Error) {
  val deps = LocalDeps.current

  when (errorScreen) {
    is PopupScreen.Error.Exception -> {
      Column(modifier = Modifier.fillMaxSize()) {
        val errorStacktrace = errorScreen.error.stackTraceToString()

        TextField(
          modifier = Modifier.fillMaxWidth().weight(1f),
          value = errorStacktrace,
          readOnly = true,
          textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace
          ),
          maxLines = Int.MAX_VALUE,
          onValueChange = { }
        )

        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
          Spacer(modifier = Modifier.weight(1f))

          Button(onClick = { deps.popPopupScreen(errorScreen) }) {
            Text("Ok")
          }

          Spacer(modifier = Modifier.width(8.dp))
        }
      }
    }
    is PopupScreen.Error.Message -> {
      Column(modifier = Modifier.fillMaxSize()) {
        TextField(
          modifier = Modifier.fillMaxWidth().weight(1f),
          value = errorScreen.errorMessage,
          readOnly = true,
          textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace
          ),
          maxLines = Int.MAX_VALUE,
          onValueChange = { }
        )

        Row(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
          Spacer(modifier = Modifier.weight(1f))

          Button(onClick = { deps.popPopupScreen(errorScreen) }) {
            Text("Ok")
          }

          Spacer(modifier = Modifier.width(8.dp))
        }
      }
    }
  }

}