package data

import androidx.annotation.ColorInt
import androidx.compose.material.ContentAlpha
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.toArgb
import toArgbHexString
import toColor
import kotlin.math.roundToInt


data class KurobaTheme(
  var name: MutableState<String> = mutableStateOf("Kuroneko"),
  var isLightTheme: MutableState<Boolean> = mutableStateOf(false),
  var lightStatusBar: MutableState<Boolean> = mutableStateOf(true),
  var lightNavBar: MutableState<Boolean> = mutableStateOf(true),
  var accentColor: MutableState<Color> = mutableStateOf(Color(0xffe0224eL)),
  var primaryColor: MutableState<Color> = mutableStateOf(Color(0xff090909L)),
  var backColor: MutableState<Color> = mutableStateOf(Color(0xff212121L)),
  var backColorSecondary: MutableState<Color> = mutableStateOf(Color(0xff171717L)),
  var errorColor: MutableState<Color> = mutableStateOf(Color(0xffff4444L)),
  var textColorPrimary: MutableState<Color> = mutableStateOf(Color(0xffaeaed6L)),
  var textColorSecondary: MutableState<Color> = mutableStateOf(Color(0xff8c8ca1L)),
  var textColorHint: MutableState<Color> = mutableStateOf(Color(0xff7b7b85L)),
  var postHighlightedColor: MutableState<Color> = mutableStateOf(Color(0x60947383L)),
  var postSavedReplyColor: MutableState<Color> = mutableStateOf(Color(0xff753ecfL)),
  var postSubjectColor: MutableState<Color> = mutableStateOf(Color(0xffd5a6bdL)),
  var postDetailsColor: MutableState<Color> = mutableStateOf(Color(0xff7b7b85L)),
  var postNameColor: MutableState<Color> = mutableStateOf(Color(0xff996878L)),
  var postInlineQuoteColor: MutableState<Color> = mutableStateOf(Color(0xff794e94L)),
  var postQuoteColor: MutableState<Color> = mutableStateOf(Color(0xffab4d63L)),
  var postHighlightQuoteColor: MutableState<Color> = mutableStateOf(Color(0xff612c38L)),
  var postLinkColor: MutableState<Color> = mutableStateOf(Color(0xffab4d7eL)),
  var postSpoilerColor: MutableState<Color> = mutableStateOf(Color(0xff000000L)),
  var postSpoilerRevealTextColor: MutableState<Color> = mutableStateOf(Color(0xffffffffL)),
  var postUnseenLabelColor: MutableState<Color> = mutableStateOf(Color(0xffbf3232L)),
  var dividerColor: MutableState<Color> = mutableStateOf(Color(0x1effffffL)),
  var bookmarkCounterNotWatchingColor: MutableState<Color> = mutableStateOf(Color(0xff898989L)),
  var bookmarkCounterHasRepliesColor: MutableState<Color> = mutableStateOf(Color(0xffff4444L)),
  var bookmarkCounterNormalColor: MutableState<Color> = mutableStateOf(Color(0xff33B5E5L)),
) {
  val defaultColors: DefaultColors
    get() = loadDefaultColors()

  fun checkErrors(errors: MutableMap<String, Unit>) {
    errors.clear()

    if (name.value.isEmpty()) {
      errors["name"] = Unit
    }
    if (accentColor.value.isUnspecified) {
      errors["accentColor"] = Unit
    }
    if (primaryColor.value.isUnspecified) {
      errors["primaryColor"] = Unit
    }
    if (backColor.value.isUnspecified) {
      errors["backColor"] = Unit
    }
    if (backColorSecondary.value.isUnspecified) {
      errors["backColorSecondary"] = Unit
    }
    if (errorColor.value.isUnspecified) {
      errors["errorColor"] = Unit
    }
    if (textColorPrimary.value.isUnspecified) {
      errors["textColorPrimary"] = Unit
    }
    if (textColorSecondary.value.isUnspecified) {
      errors["textColorSecondary"] = Unit
    }
    if (textColorHint.value.isUnspecified) {
      errors["textColorHint"] = Unit
    }
    if (postHighlightedColor.value.isUnspecified) {
      errors["postHighlightedColor"] = Unit
    }
    if (postSavedReplyColor.value.isUnspecified) {
      errors["postSavedReplyColor"] = Unit
    }
    if (postSubjectColor.value.isUnspecified) {
      errors["postSubjectColor"] = Unit
    }
    if (postDetailsColor.value.isUnspecified) {
      errors["postDetailsColor"] = Unit
    }
    if (postNameColor.value.isUnspecified) {
      errors["postNameColor"] = Unit
    }
    if (postInlineQuoteColor.value.isUnspecified) {
      errors["postInlineQuoteColor"] = Unit
    }
    if (postQuoteColor.value.isUnspecified) {
      errors["postQuoteColor"] = Unit
    }
    if (postHighlightQuoteColor.value.isUnspecified) {
      errors["postHighlightQuoteColor"] = Unit
    }
    if (postLinkColor.value.isUnspecified) {
      errors["postLinkColor"] = Unit
    }
    if (postSpoilerColor.value.isUnspecified) {
      errors["postSpoilerColor"] = Unit
    }
    if (postSpoilerRevealTextColor.value.isUnspecified) {
      errors["postSpoilerRevealTextColor"] = Unit
    }
    if (postUnseenLabelColor.value.isUnspecified) {
      errors["postUnseenLabelColor"] = Unit
    }
    if (dividerColor.value.isUnspecified) {
      errors["dividerColor"] = Unit
    }
    if (bookmarkCounterNotWatchingColor.value.isUnspecified) {
      errors["bookmarkCounterNotWatchingColor"] = Unit
    }
    if (bookmarkCounterHasRepliesColor.value.isUnspecified) {
      errors["bookmarkCounterHasRepliesColor"] = Unit
    }
    if (bookmarkCounterNormalColor.value.isUnspecified) {
      errors["bookmarkCounterNormalColor"] = Unit
    }
  }

  fun fromKurobaThemeJson(kurobaThemeJson: KurobaThemeJson) {
    val accentColorNew = kurobaThemeJson.accentColor?.toColor()
      ?: error("Failed to parse 'accentColor', rawColor='${kurobaThemeJson.accentColor}'")
    val primaryColorNew = kurobaThemeJson.primaryColor?.toColor()
      ?: error("Failed to parse 'primaryColor', rawColor='${kurobaThemeJson.primaryColor}'")
    val backColorNew = kurobaThemeJson.backColor?.toColor()
      ?: error("Failed to parse 'backColor', rawColor='${kurobaThemeJson.backColor}'")
    val backColorSecondaryNew = kurobaThemeJson.backColorSecondary?.toColor()
      ?: error("Failed to parse 'backColorSecondary', rawColor='${kurobaThemeJson.backColorSecondary}'")
    val errorColorNew = kurobaThemeJson.errorColor?.toColor()
      ?: error("Failed to parse 'errorColor', rawColor='${kurobaThemeJson.errorColor}'")
    val textColorPrimaryNew = kurobaThemeJson.textColorPrimary?.toColor()
      ?: error("Failed to parse 'textColorPrimary', rawColor='${kurobaThemeJson.textColorPrimary}'")
    val textColorSecondaryNew = kurobaThemeJson.textColorSecondary?.toColor()
      ?: error("Failed to parse 'textColorSecondary', rawColor='${kurobaThemeJson.textColorSecondary}'")
    val textColorHintNew = kurobaThemeJson.textColorHint?.toColor()
      ?: error("Failed to parse 'textColorHint', rawColor='${kurobaThemeJson.textColorHint}'")
    val postHighlightedColorNew = kurobaThemeJson.postHighlightedColor?.toColor()
      ?: error("Failed to parse 'postHighlightedColor', rawColor='${kurobaThemeJson.postHighlightedColor}'")
    val postSavedReplyColorNew = kurobaThemeJson.postSavedReplyColor?.toColor()
      ?: error("Failed to parse 'postSavedReplyColor', rawColor='${kurobaThemeJson.postSavedReplyColor}'")
    val postSubjectColorNew = kurobaThemeJson.postSubjectColor?.toColor()
      ?: error("Failed to parse 'postSubjectColor', rawColor='${kurobaThemeJson.postSubjectColor}'")
    val postDetailsColorNew = kurobaThemeJson.postDetailsColor?.toColor()
      ?: error("Failed to parse 'postDetailsColor', rawColor='${kurobaThemeJson.postDetailsColor}'")
    val postNameColorNew = kurobaThemeJson.postNameColor?.toColor()
      ?: error("Failed to parse 'postNameColor', rawColor='${kurobaThemeJson.postNameColor}'")
    val postInlineQuoteColorNew = kurobaThemeJson.postInlineQuoteColor?.toColor()
      ?: error("Failed to parse 'postInlineQuoteColor', rawColor='${kurobaThemeJson.postInlineQuoteColor}'")
    val postQuoteColorNew = kurobaThemeJson.postQuoteColor?.toColor()
      ?: error("Failed to parse 'postQuoteColor', rawColor='${kurobaThemeJson.postQuoteColor}'")
    val postHighlightQuoteColorNew = kurobaThemeJson.postHighlightQuoteColor?.toColor()
      ?: error("Failed to parse 'postHighlightQuoteColor', rawColor='${kurobaThemeJson.postHighlightQuoteColor}'")
    val postLinkColorNew = kurobaThemeJson.postLinkColor?.toColor()
      ?: error("Failed to parse 'postLinkColor', rawColor='${kurobaThemeJson.postLinkColor}'")
    val postSpoilerColorNew = kurobaThemeJson.postSpoilerColor?.toColor()
      ?: error("Failed to parse 'postSpoilerColor', rawColor='${kurobaThemeJson.postSpoilerColor}'")
    val postSpoilerRevealTextColorNew = kurobaThemeJson.postSpoilerRevealTextColor?.toColor()
      ?: error("Failed to parse 'postSpoilerRevealTextColor', rawColor='${kurobaThemeJson.postSpoilerRevealTextColor}'")
    val postUnseenLabelColorNew = kurobaThemeJson.postUnseenLabelColor?.toColor()
      ?: error("Failed to parse 'postUnseenLabelColor', rawColor='${kurobaThemeJson.postUnseenLabelColor}'")
    val dividerColorNew = kurobaThemeJson.dividerColor?.toColor()
      ?: error("Failed to parse 'dividerColor', rawColor='${kurobaThemeJson.dividerColor}'")
    val bookmarkCounterNotWatchingColorNew = kurobaThemeJson.bookmarkCounterNotWatchingColor?.toColor()
      ?: error("Failed to parse 'bookmarkCounterNotWatchingColor', rawColor='${kurobaThemeJson.bookmarkCounterNotWatchingColor}'")
    val bookmarkCounterHasRepliesColorNew = kurobaThemeJson.bookmarkCounterHasRepliesColor?.toColor()
      ?: error("Failed to parse 'bookmarkCounterHasRepliesColor', rawColor='${kurobaThemeJson.bookmarkCounterHasRepliesColor}'")
    val bookmarkCounterNormalColorNew = kurobaThemeJson.bookmarkCounterNormalColor?.toColor()
      ?: error("Failed to parse 'bookmarkCounterNormalColor', rawColor='${kurobaThemeJson.bookmarkCounterNormalColor}'")

    name.value = kurobaThemeJson.name
    isLightTheme.value = kurobaThemeJson.isLightTheme
    lightStatusBar.value = kurobaThemeJson.lightStatusBar
    lightNavBar.value = kurobaThemeJson.lightNavBar

    accentColor.value = accentColorNew
    primaryColor.value = primaryColorNew
    backColor.value = backColorNew
    backColorSecondary.value = backColorSecondaryNew
    errorColor.value = errorColorNew
    textColorPrimary.value = textColorPrimaryNew
    textColorSecondary.value = textColorSecondaryNew
    textColorHint.value = textColorHintNew
    postHighlightedColor.value = postHighlightedColorNew
    postSavedReplyColor.value = postSavedReplyColorNew
    postSubjectColor.value = postSubjectColorNew
    postDetailsColor.value = postDetailsColorNew
    postNameColor.value = postNameColorNew
    postInlineQuoteColor.value = postInlineQuoteColorNew
    postQuoteColor.value = postQuoteColorNew
    postHighlightQuoteColor.value = postHighlightQuoteColorNew
    postLinkColor.value = postLinkColorNew
    postSpoilerColor.value = postSpoilerColorNew
    postSpoilerRevealTextColor.value = postSpoilerRevealTextColorNew
    postUnseenLabelColor.value = postUnseenLabelColorNew
    dividerColor.value = dividerColorNew
    bookmarkCounterNotWatchingColor.value = bookmarkCounterNotWatchingColorNew
    bookmarkCounterHasRepliesColor.value = bookmarkCounterHasRepliesColorNew
    bookmarkCounterNormalColor.value = bookmarkCounterNormalColorNew
  }

  fun toKurobaThemeJson(): KurobaThemeJson {
    return KurobaThemeJson(
      name = name.value,
      isLightTheme = isLightTheme.value,
      lightStatusBar = lightStatusBar.value,
      lightNavBar = lightNavBar.value,
      accentColor = "#${accentColor.value.toArgbHexString()}",
      primaryColor = "#${primaryColor.value.toArgbHexString()}",
      backColor = "#${backColor.value.toArgbHexString()}",
      backColorSecondary = "#${backColorSecondary.value.toArgbHexString()}",
      errorColor = "#${errorColor.value.toArgbHexString()}",
      textColorPrimary = "#${textColorPrimary.value.toArgbHexString()}",
      textColorSecondary = "#${textColorSecondary.value.toArgbHexString()}",
      textColorHint = "#${textColorHint.value.toArgbHexString()}",
      postHighlightedColor = "#${postHighlightedColor.value.toArgbHexString()}",
      postSavedReplyColor = "#${postSavedReplyColor.value.toArgbHexString()}",
      postSubjectColor = "#${postSubjectColor.value.toArgbHexString()}",
      postDetailsColor = "#${postDetailsColor.value.toArgbHexString()}",
      postNameColor = "#${postNameColor.value.toArgbHexString()}",
      postInlineQuoteColor = "#${postInlineQuoteColor.value.toArgbHexString()}",
      postQuoteColor = "#${postQuoteColor.value.toArgbHexString()}",
      postHighlightQuoteColor = "#${postHighlightQuoteColor.value.toArgbHexString()}",
      postLinkColor = "#${postLinkColor.value.toArgbHexString()}",
      postSpoilerColor = "#${postSpoilerColor.value.toArgbHexString()}",
      postSpoilerRevealTextColor = "#${postSpoilerRevealTextColor.value.toArgbHexString()}",
      postUnseenLabelColor = "#${postUnseenLabelColor.value.toArgbHexString()}",
      dividerColor = "#${dividerColor.value.toArgbHexString()}",
      bookmarkCounterNotWatchingColor = "#${bookmarkCounterNotWatchingColor.value.toArgbHexString()}",
      bookmarkCounterHasRepliesColor = "#${bookmarkCounterHasRepliesColor.value.toArgbHexString()}",
      bookmarkCounterNormalColor = "#${bookmarkCounterNormalColor.value.toArgbHexString()}",
    )
  }

  private fun loadDefaultColors(): DefaultColors {
    val controlNormalColor = if (isLightTheme.value) {
      CONTROL_LIGHT_COLOR
    } else {
      CONTROL_DARK_COLOR
    }

    val disabledControlAlpha = (255f * .4f).toInt()

    return DefaultColors(
      disabledControlAlpha = disabledControlAlpha,
      controlNormalColor = controlNormalColor,
      controlNormalColorCompose = Color(controlNormalColor)
    )
  }

  @Composable
  fun switchColors(): SwitchColors {
    val checkedThumbColor = accentColor.value
    val uncheckedThumbColor = remember(key1 = defaultColors.controlNormalColorCompose) {
      manipulateColor(defaultColors.controlNormalColorCompose, 1.2f)
    }
    val uncheckedTrackColor = remember(key1 = defaultColors.controlNormalColorCompose) {
      manipulateColor(defaultColors.controlNormalColorCompose, .6f)
    }

    return SwitchDefaults.colors(
      checkedThumbColor = checkedThumbColor,
      checkedTrackColor = checkedThumbColor,
      checkedTrackAlpha = 0.54f,
      uncheckedThumbColor = uncheckedThumbColor,
      uncheckedTrackColor = uncheckedTrackColor,
      uncheckedTrackAlpha = 0.38f,
      disabledCheckedThumbColor = checkedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(uncheckedThumbColor),
      disabledCheckedTrackColor = checkedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(uncheckedThumbColor),
      disabledUncheckedThumbColor = uncheckedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(uncheckedThumbColor),
      disabledUncheckedTrackColor = uncheckedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(uncheckedThumbColor)
    )
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as KurobaTheme

    if (name.value != other.name.value) return false
    if (isLightTheme.value != other.isLightTheme.value) return false
    if (lightStatusBar.value != other.lightStatusBar.value) return false
    if (lightNavBar.value != other.lightNavBar.value) return false
    if (accentColor.value != other.accentColor.value) return false
    if (primaryColor.value != other.primaryColor.value) return false
    if (backColor.value != other.backColor.value) return false
    if (backColorSecondary.value != other.backColorSecondary.value) return false
    if (errorColor.value != other.errorColor.value) return false
    if (textColorPrimary.value != other.textColorPrimary.value) return false
    if (textColorSecondary.value != other.textColorSecondary.value) return false
    if (textColorHint.value != other.textColorHint.value) return false
    if (postHighlightedColor.value != other.postHighlightedColor.value) return false
    if (postSavedReplyColor.value != other.postSavedReplyColor.value) return false
    if (postSubjectColor.value != other.postSubjectColor.value) return false
    if (postDetailsColor.value != other.postDetailsColor.value) return false
    if (postNameColor.value != other.postNameColor.value) return false
    if (postInlineQuoteColor.value != other.postInlineQuoteColor.value) return false
    if (postQuoteColor.value != other.postQuoteColor.value) return false
    if (postHighlightQuoteColor.value != other.postHighlightQuoteColor.value) return false
    if (postLinkColor.value != other.postLinkColor.value) return false
    if (postSpoilerColor.value != other.postSpoilerColor.value) return false
    if (postSpoilerRevealTextColor.value != other.postSpoilerRevealTextColor.value) return false
    if (postUnseenLabelColor.value != other.postUnseenLabelColor.value) return false
    if (dividerColor.value != other.dividerColor.value) return false
    if (bookmarkCounterNotWatchingColor.value != other.bookmarkCounterNotWatchingColor.value) return false
    if (bookmarkCounterHasRepliesColor.value != other.bookmarkCounterHasRepliesColor.value) return false
    if (bookmarkCounterNormalColor.value != other.bookmarkCounterNormalColor.value) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.value.hashCode()
    result = 31 * result + isLightTheme.value.hashCode()
    result = 31 * result + lightStatusBar.value.hashCode()
    result = 31 * result + lightNavBar.value.hashCode()
    result = 31 * result + accentColor.value.hashCode()
    result = 31 * result + primaryColor.value.hashCode()
    result = 31 * result + backColor.value.hashCode()
    result = 31 * result + backColorSecondary.value.hashCode()
    result = 31 * result + errorColor.value.hashCode()
    result = 31 * result + textColorPrimary.value.hashCode()
    result = 31 * result + textColorSecondary.value.hashCode()
    result = 31 * result + textColorHint.value.hashCode()
    result = 31 * result + postHighlightedColor.value.hashCode()
    result = 31 * result + postSavedReplyColor.value.hashCode()
    result = 31 * result + postSubjectColor.value.hashCode()
    result = 31 * result + postDetailsColor.value.hashCode()
    result = 31 * result + postNameColor.value.hashCode()
    result = 31 * result + postInlineQuoteColor.value.hashCode()
    result = 31 * result + postQuoteColor.value.hashCode()
    result = 31 * result + postHighlightQuoteColor.value.hashCode()
    result = 31 * result + postLinkColor.value.hashCode()
    result = 31 * result + postSpoilerColor.value.hashCode()
    result = 31 * result + postSpoilerRevealTextColor.value.hashCode()
    result = 31 * result + postUnseenLabelColor.value.hashCode()
    result = 31 * result + dividerColor.value.hashCode()
    result = 31 * result + bookmarkCounterNotWatchingColor.value.hashCode()
    result = 31 * result + bookmarkCounterHasRepliesColor.value.hashCode()
    result = 31 * result + bookmarkCounterNormalColor.value.hashCode()
    return result
  }

  data class DefaultColors(
    val disabledControlAlpha: Int,
    val controlNormalColor: Int,
    val controlNormalColorCompose: Color,
  ) {

    val disabledControlAlphaFloat: Float
      get() = disabledControlAlpha.toFloat() / MAX_ALPHA_FLOAT
  }

  companion object {
    private const val CONTROL_LIGHT_COLOR = 0xFFAAAAAAL.toInt()
    private const val CONTROL_DARK_COLOR = 0xFFCCCCCCL.toInt()
    private const val MAX_ALPHA_FLOAT = 255f
    private val TEMP_ARRAY = ThreadLocal<DoubleArray>()

    fun manipulateColor(color: Color, factor: Float): Color {
      return Color(manipulateColor(color.toArgb(), factor))
    }

    fun manipulateColor(color: Int, factor: Float): Int {
      val a = color.alpha()
      val r = (color.red() * factor).roundToInt()
      val g = (color.green() * factor).roundToInt()
      val b = (color.blue() * factor).roundToInt()
      return argb(a, Math.min(r, 255), Math.min(g, 255), Math.min(b, 255))
    }

    @ColorInt
    private fun argb(alpha: Int, red: Int, green: Int, blue: Int): Int {
      return alpha shl 24 or (red shl 16) or (green shl 8) or blue
    }

    fun Int.alpha(): Int {
      return this ushr 24
    }

    fun Int.red(): Int {
      return this shr 16 and 0xFF
    }
    fun Int.green(): Int {
      return this shr 8 and 0xFF
    }

    fun Int.blue(): Int {
      return this and 0xFF
    }

    @JvmStatic
    fun isDarkColor(color: ULong): Boolean {
      return isDarkColor(color.toInt())
    }

    @JvmStatic
    fun isDarkColor(color: Color): Boolean {
      return isDarkColor(color.toArgb())
    }

    @JvmStatic
    fun isDarkColor(color: Int): Boolean {
      return calculateLuminance(color) < 0.5f
    }

    private fun calculateLuminance(@ColorInt color: Int): Double {
      val result: DoubleArray = getTempDouble3Array()
      colorToXYZ(color, result)
      // Luminance is the Y component
      return result[1] / 100
    }

    private fun colorToXYZ(@ColorInt color: Int, outXyz: DoubleArray) {
      RGBToXYZ(color.red(), color.green(), color.blue(), outXyz)
    }

    private fun RGBToXYZ(r: Int, g: Int, b: Int, outXyz: DoubleArray) {
      require(outXyz.size == 3) { "outXyz must have a length of 3." }
      var sr = r / 255.0
      sr = if (sr < 0.04045) sr / 12.92 else Math.pow((sr + 0.055) / 1.055, 2.4)
      var sg = g / 255.0
      sg = if (sg < 0.04045) sg / 12.92 else Math.pow((sg + 0.055) / 1.055, 2.4)
      var sb = b / 255.0
      sb = if (sb < 0.04045) sb / 12.92 else Math.pow((sb + 0.055) / 1.055, 2.4)
      outXyz[0] = 100 * (sr * 0.4124 + sg * 0.3576 + sb * 0.1805)
      outXyz[1] = 100 * (sr * 0.2126 + sg * 0.7152 + sb * 0.0722)
      outXyz[2] = 100 * (sr * 0.0193 + sg * 0.1192 + sb * 0.9505)
    }

    private fun getTempDouble3Array(): DoubleArray {
      var result = TEMP_ARRAY.get()
      if (result == null) {
        result = DoubleArray(3)
        TEMP_ARRAY.set(result)
      }
      return result
    }

  }

}