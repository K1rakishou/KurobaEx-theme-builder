import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.KurobaTheme
import screens.*

@Composable
fun BoxScope.ApplicationContent() {
  val kurobaTheme = LocalKurobaTheme.current
  val primaryColor by kurobaTheme.primaryColor
  val state = remember { State() }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = primaryColor)
  ) {
    Box(modifier = Modifier.height(52.dp)) {
      ToolbarContent(
        state = state,
        kurobaTheme = kurobaTheme
      )
    }

    Box(modifier = Modifier.weight(1f)) {
      KurobaContent(state = state)
    }

    Box(modifier = Modifier.height(52.dp)) {
      BottomNavigationContent(
        state = state,
        kurobaTheme = kurobaTheme,
        onScreenChanged = { newScreen ->
          if (state.currentScreen.value == newScreen) {
            return@BottomNavigationContent
          }

          state.currentScreen.value = newScreen
        }
      )
    }
  }

}

@Composable
private fun BoxScope.KurobaContent(state: State) {
  Box(modifier = Modifier.fillMaxSize()) {
    val currentScreen by state.currentScreen

    Crossfade(
      targetState = currentScreen,
      animationSpec = tween(easing = LinearEasing)
    ) {
      when (currentScreen) {
        Screen.Search -> SearchScreen()
        Screen.Archive -> ArchiveScreen()
        Screen.Bookmarks -> BookmarksScreen()
        Screen.Browse -> BrowseScreen()
        Screen.Settings -> SettingsScreen()
      }
    }
  }
}

@Composable
private fun BoxScope.BottomNavigationContent(
  state: State,
  kurobaTheme: KurobaTheme,
  onScreenChanged: (Screen) -> Unit
) {
  val primaryColor by kurobaTheme.primaryColor

  Row(
    modifier = Modifier
      .fillMaxSize()
      .background(color = primaryColor)
  ) {
    Screen.values().forEach { screen ->
      val weight = 1f / Screen.values().size.toFloat()

      Box(
        modifier = Modifier
          .fillMaxHeight()
          .weight(weight)
          .clickable { onScreenChanged(screen) }
      ) {
        Text(
          modifier = Modifier
            .wrapContentSize()
            .align(Alignment.Center),
          maxLines = 2,
          fontSize = 14.sp,
          text = screen.screenName,
          color = Color.White
        )
      }
    }
  }
}

@Composable
private fun BoxScope.ToolbarContent(state: State, kurobaTheme: KurobaTheme) {
  val currentScreen by state.currentScreen
  val primaryColor by kurobaTheme.primaryColor
  val toolbarTitle = currentScreen.screenName

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = primaryColor)
  ) {
    Text(
      modifier = Modifier
        .wrapContentSize()
        .align(Alignment.Center),
      fontSize = 20.sp,
      text = toolbarTitle,
      color = Color.White
    )
  }
}

private enum class Screen(val screenName: String) {
  Search("Search"),
  Archive("Archive"),
  Bookmarks("Bookmarks"),
  Browse("Browse"),
  Settings("Settings")
}

private class State {
  var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Browse)
}