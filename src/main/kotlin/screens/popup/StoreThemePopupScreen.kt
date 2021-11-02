package screens.popup

import Deps
import LocalKurobaTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun StoreThemePopupScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val gson = Deps.gsonPrettyPrint

  Box(modifier = Modifier.fillMaxSize()) {
    val themeJson = remember(key1 = kurobaTheme) {
      gson.toJson(kurobaTheme.toKurobaThemeJson())
    }

    TextField(
      modifier = Modifier.fillMaxSize(),
      value = themeJson,
      readOnly = true,
      textStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace
      ),
      maxLines = Int.MAX_VALUE,
      onValueChange = { }
    )
  }
}