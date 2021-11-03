// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import data.KurobaTheme
import screens.popup.ErrorMessagePopupScreen
import screens.popup.LoadThemePopupScreen
import screens.popup.PopupScreen
import screens.popup.SaveThemePopupScreen


val LocalKurobaTheme = staticCompositionLocalOf<KurobaTheme> { error("KurobaTheme is not provided") }
val LocalDeps = staticCompositionLocalOf<Deps> { Deps }

fun main() = application {
  val windowState = rememberWindowState(
    size = DpSize(1400.dp, 960.dp),
    position = WindowPosition.Aligned(alignment = Alignment.Center)
  )

  Window(
    title = "KurobaEx theme builder",
    onCloseRequest = ::exitApplication,
    state = windowState
  ) {
    MainContent()
  }
}

@Composable
fun MainContent() {
  MaterialTheme {
    val kurobaTheme = remember { KurobaTheme() }

    CompositionLocalProvider(LocalKurobaTheme provides kurobaTheme) {
      CompositionLocalProvider(LocalDeps provides Deps) {
        Row(modifier = Modifier.fillMaxSize()) {
          val deps = LocalDeps.current

          ProcessPopupEvents(deps)

          Box(modifier = Modifier.weight(0.7f)) {
            ApplicationContent()
          }

          Box(
            modifier = Modifier
              .width(1.dp)
              .fillMaxHeight()
              .background(Color.White)
          )

          Box(modifier = Modifier.weight(0.3f)) {
            ThemeEditorContent()
          }
        }
      }
    }
  }
}

@Composable
private fun ProcessPopupEvents(deps: Deps) {
  val popups = deps.popupScreensStack
  if (popups.isEmpty()) {
    return
  }

  val topPopup = popups.lastOrNull()
    ?: return

  for ((index, popupScreen) in popups.withIndex()) {

    fun closeTopPopup(popupScreen: PopupScreen) {
      deps.popPopupScreen(popupScreen)
    }

    Popup(
      focusable = index == popups.lastIndex,
      onDismissRequest = { closeTopPopup(topPopup) }
    ) {
      DisposableEffect(key1 = true) {
        onDispose { closeTopPopup(topPopup) }
      }

      val color = remember { Color(0x88000000) }

      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(color)
          .kurobaClickable(onClick = { closeTopPopup(topPopup) })
      ) {
        Column(
          modifier = Modifier
            .width(popupScreen.maxWidth)
            .height(popupScreen.maxHeight)
            .background(Color.LightGray)
            .align(Alignment.Center)
            .consumeClicks()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(42.dp)
              .background(Color.Black)
          ) {

            Spacer(modifier = Modifier.width(16.dp))

            Text(
              modifier = Modifier
                .align(Alignment.CenterVertically),
              text = popupScreen.title,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              fontSize = 18.sp,
              color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
              modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterVertically)
                .clickable { closeTopPopup(topPopup) },
              colorFilter = ColorFilter.tint(Color.White),
              imageVector = Icons.Filled.Close,
              contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))
          }

          PopupContent(popupScreen)
        }
      }
    }
  }
}

@Composable
private fun PopupContent(popupScreen: PopupScreen) {
  val deps = LocalDeps.current

  val errorPopupState = remember { mutableStateOf<PopupScreen.Error?>(null) }
  var errorPopup by errorPopupState

  when (val screen = popupScreen) {
    is PopupScreen.LoadTheme -> {
      LoadThemePopupScreen(
        popupScreen = screen,
        showErrorPopup = { popupScreenError -> errorPopupState.value = popupScreenError }
      )
    }
    is PopupScreen.SaveTheme -> {
      SaveThemePopupScreen()
    }
    is PopupScreen.Error -> {
      ErrorMessagePopupScreen(screen)
    }
  }

  val errPopup = errorPopup
  if (errPopup != null) {
    DisposableEffect(key1 = errPopup) {
      deps.pushPopupScreen(errPopup)
      onDispose { errorPopup = null }
    }
  }
}