import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.toArgb
import kotlin.math.roundToInt

fun Modifier.consumeClicks(): Modifier {
  return composed {
    clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = null,
      onClick = { /** no-op */ }
    )
  }
}

fun Modifier.kurobaClickable(onClick: () -> Unit): Modifier {
  return composed {
    clickable(
      interactionSource = remember { MutableInteractionSource() },
      indication = null,
      onClick = { onClick() }
    )
  }
}

fun String.toColor(forceAlpha: Boolean = true): Color? {
  val colorStr = this.removePrefix("#")
  if (colorStr.isEmpty()) {
    return Color.Unspecified
  }

  val argb = colorStr.toLongOrNull(radix = 16)
    ?: return null

  val newColor = Color(argb)
  if (newColor.alpha > 0f || !forceAlpha) {
    return newColor
  }

  val red = (newColor.red * 255f).roundToInt()
  val green = (newColor.green * 255f).roundToInt()
  val blue = (newColor.blue * 255f).roundToInt()

  return Color(red = red, green = green, blue = blue, alpha = 0xFF)
}

fun Color.toArgbHexString(): String {
  if (this.isUnspecified) {
    return ""
  }

  return this.toArgb().toUInt().toString(radix = 16)
}