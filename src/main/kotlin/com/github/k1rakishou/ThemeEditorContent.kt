package com.github.k1rakishou

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.k1rakishou.screens.popup.PopupScreen
import java.util.*

private val HEX_NUMBER_CHARACTERS = setOf("A", "B", "C", "D", "E", "F")

@Composable
fun BoxScope.ThemeEditorContent() {
  val deps = LocalDeps.current
  val gson = Deps.gson

  BuildContentInternal(
    onLoadThemeClicked = { Deps.pushPopupScreen(PopupScreen.LoadTheme) },
    onSaveThemeClicked = { Deps.pushPopupScreen(PopupScreen.SaveTheme) },
  )
}

@Composable
private fun BuildContentInternal(
  onLoadThemeClicked: () -> Unit,
  onSaveThemeClicked: () -> Unit
) {
  val kurobaTheme = LocalKurobaTheme.current

  Row(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Gray)
  ) {
    val scrollState = rememberScrollState()
    val errors = remember { mutableStateMapOf<String, Unit>() }

    Column(
      modifier = Modifier
        .weight(1f)
        .verticalScroll(state = scrollState)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        Spacer(modifier = Modifier.width(4.dp))

        Button(onClick = { onLoadThemeClicked.invoke() }) {
          Text("Load")
        }

        Spacer(modifier = Modifier.width(10.dp))

        Button(
          enabled = errors.isEmpty(),
          onClick = { onSaveThemeClicked.invoke() }
        ) {
          Text("Save")
        }
      }

      Spacer(modifier = Modifier.height(4.dp))

      ThemeName(name = "Theme name", valueState = kurobaTheme.name)

      CheckBox(name = "isLightTheme", flagState = kurobaTheme.isLightTheme)
      CheckBox(name = "lightStatusBar", flagState = kurobaTheme.lightStatusBar)
      CheckBox(name = "lightNavBar", flagState = kurobaTheme.lightNavBar)

      ColorInput(name = "accentColor", colorState = kurobaTheme.accentColor)
      ColorInput(name = "primaryColor", colorState = kurobaTheme.primaryColor)
      ColorInput(name = "backColor", colorState = kurobaTheme.backColor)
      ColorInput(name = "backColorSecondary", colorState = kurobaTheme.backColorSecondary)
      ColorInput(name = "errorColor", colorState = kurobaTheme.errorColor)
      ColorInput(name = "textColorPrimary", colorState = kurobaTheme.textColorPrimary)
      ColorInput(name = "textColorSecondary", colorState = kurobaTheme.textColorSecondary)
      ColorInput(name = "textColorHint", colorState = kurobaTheme.textColorHint)
      ColorInput(name = "postHighlightedColor", colorState = kurobaTheme.postHighlightedColor)
      ColorInput(name = "postSavedReplyColor", colorState = kurobaTheme.postSavedReplyColor)
      ColorInput(name = "postSubjectColor", colorState = kurobaTheme.postSubjectColor)
      ColorInput(name = "postDetailsColor", colorState = kurobaTheme.postDetailsColor)
      ColorInput(name = "postNameColor", colorState = kurobaTheme.postNameColor)
      ColorInput(name = "postInlineQuoteColor", colorState = kurobaTheme.postInlineQuoteColor)
      ColorInput(name = "postQuoteColor", colorState = kurobaTheme.postQuoteColor)
      ColorInput(name = "postHighlightQuoteColor", colorState = kurobaTheme.postHighlightQuoteColor)
      ColorInput(name = "postLinkColor", colorState = kurobaTheme.postLinkColor)
      ColorInput(name = "postSpoilerColor", colorState = kurobaTheme.postSpoilerColor)
      ColorInput(name = "postSpoilerRevealTextColor", colorState = kurobaTheme.postSpoilerRevealTextColor)
      ColorInput(name = "postUnseenLabelColor", colorState = kurobaTheme.postUnseenLabelColor)
      ColorInput(name = "dividerColor", colorState = kurobaTheme.dividerColor)
      ColorInput(name = "bookmarkCounterNotWatchingColor", colorState = kurobaTheme.bookmarkCounterNotWatchingColor)
      ColorInput(name = "bookmarkCounterHasRepliesColor", colorState = kurobaTheme.bookmarkCounterHasRepliesColor)
      ColorInput(name = "bookmarkCounterNormalColor", colorState = kurobaTheme.bookmarkCounterNormalColor)

      kurobaTheme.checkErrors(errors)
    }

    val scrollbarStyle = remember {
      ScrollbarStyle(
        minimalHeight = 24.dp,
        thickness = 16.dp,
        shape = RoundedCornerShape(1.dp),
        hoverDurationMillis = 300,
        unhoverColor = Color.Black.copy(alpha = 0.3f),
        hoverColor = Color.Black.copy(alpha = 0.6f)
      )
    }

    VerticalScrollbar(
      modifier = Modifier
        .align(Alignment.CenterVertically)
        .fillMaxHeight(),
      style = scrollbarStyle,
      adapter = rememberScrollbarAdapter(scrollState)
    )
  }
}

@Composable
private fun ThemeName(
  name: String,
  valueState: MutableState<String>
) {
  Column(
    modifier = Modifier
      .wrapContentHeight()
      .fillMaxWidth()
  ) {
    Text(
      modifier = Modifier
        .padding(start = 4.dp, top = 4.dp),
      text = name
    )
    val textStyle = remember {
      TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily.Monospace
      )
    }

    Row(
      modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
    ) {
      TextField(
        modifier = Modifier
          .wrapContentHeight()
          .weight(1f),
        textStyle = textStyle,
        singleLine = true,
        value = valueState.value,
        onValueChange = { newThemeName ->
          valueState.value = newThemeName
        }
      )

      Spacer(modifier = Modifier.width(4.dp))

      if (valueState.value.isEmpty()) {
        Image(
          imageVector = Icons.Outlined.Warning,
          colorFilter = ColorFilter.tint(Color.Red),
          contentDescription = null,
          modifier = Modifier
            .size(24.dp)
            .align(Alignment.CenterVertically)
        )
      } else {
        Spacer(
          modifier = Modifier
            .size(24.dp)
            .align(Alignment.CenterVertically)
        )
      }

      Spacer(modifier = Modifier.width(4.dp))
    }
  }
}

@Composable
private fun CheckBox(
  name: String,
  flagState: MutableState<Boolean>
) {
  Row(
    modifier = Modifier
      .wrapContentHeight()
      .fillMaxWidth()
  ) {
    Text(
      modifier = Modifier
        .padding(start = 4.dp, top = 4.dp)
        .align(Alignment.CenterVertically),
      text = name
    )

    Spacer(modifier = Modifier.width(8.dp))

    Checkbox(
      checked = flagState.value,
      onCheckedChange = { nowChecked -> flagState.value = nowChecked }
    )
  }
}

@Composable
private fun ColorInput(
  name: String,
  colorState: MutableState<Color>
) {
  var hasError by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .wrapContentHeight()
      .fillMaxWidth()
  ) {
    Text(
      modifier = Modifier
        .padding(start = 4.dp, top = 4.dp),
      text = name
    )

    Row(
      modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
    ) {
      var color by colorState
      val textStyle = remember {
        TextStyle(
          fontSize = 16.sp,
          fontFamily = FontFamily.Monospace
        )
      }

      TextField(
        modifier = Modifier
          .wrapContentHeight()
          .weight(1f),
        textStyle = textStyle,
        singleLine = true,
        value = color.toArgbHexString(),
        onValueChange = { newColorString ->
          if (newColorString.length > 8) {
            return@TextField
          }

          val allDigits = newColorString.all { ch ->
            ch.isDigit() || ch.uppercase(Locale.ENGLISH) in HEX_NUMBER_CHARACTERS
          }

          if (!allDigits) {
            return@TextField
          }

          val newColor = newColorString.toColor(forceAlpha = false)
          hasError = newColor == null || newColor.isUnspecified || newColor.alpha <= 0f

          if (newColor != null) {
            color = newColor
          }
        }
      )

      Spacer(modifier = Modifier.width(4.dp))

      if (hasError) {
        Image(
          imageVector = Icons.Outlined.Warning,
          colorFilter = ColorFilter.tint(Color.Red),
          contentDescription = null,
          modifier = Modifier
            .size(24.dp)
            .align(Alignment.CenterVertically)
        )
      } else {
        val bgColor = if (color.isUnspecified) {
          Color.Magenta
        } else {
          color
        }

        Box(
          modifier = Modifier
            .size(24.dp)
            .background(bgColor)
            .border(width = 1.dp, color = Color.Black)
            .align(Alignment.CenterVertically)
        )
      }

      Spacer(modifier = Modifier.width(4.dp))
    }
  }
}