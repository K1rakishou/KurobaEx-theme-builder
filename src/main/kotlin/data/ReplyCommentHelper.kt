package data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import java.util.regex.Pattern

object ReplyCommentHelper {
  private val QUOTE_MATCHER = Pattern.compile(">>\\d+")
  private val BOARD_LINK_MATCHER = Pattern.compile(">>>\\/\\w+\\/")
  private val URL_PATTERN = Pattern.compile("\\S*:\\/\\/\\S+")
  private val CLOSED_SPOILER_PATTERN = Pattern.compile("_Closed spoiler_")
  private val OPENED_SPOILER_PATTERN = Pattern.compile("_Opened spoiler_")

  fun processReplyCommentHighlight(
    commentText: String,
    textColorPrimary: Color,
    accentColor: Color,
    postInlineQuoteColor: Color,
    postLinkColor: Color,
    postQuoteColor: Color,
    postHighlightQuoteColor: Color,
    postSpoilerColor: Color,
    postSpoilerRevealTextColor: Color,
  ): AnnotatedString {
    var offset = 0
    val newLine = "\n"
    val newLineLen = newLine.length
    val commentTextAnnotatedBuilder = AnnotatedString.Builder(commentText)

    commentTextAnnotatedBuilder.addStyle(
      SpanStyle(color = textColorPrimary),
      0,
      commentTextAnnotatedBuilder.length
    )

    val factor = if (KurobaTheme.isDarkColor(accentColor)) {
      1.2f
    } else {
      0.8f
    }

    val postInlineQuoteColorUpdated = KurobaTheme.manipulateColor(postInlineQuoteColor, factor)
    val postLinkColorUpdated = KurobaTheme.manipulateColor(postLinkColor, factor)
    val postQuoteColorUpdated = KurobaTheme.manipulateColor(postQuoteColor, factor)
    val postHighlightQuoteColorUpdated = KurobaTheme.manipulateColor(postHighlightQuoteColor, factor)

    for (line in commentText.split(newLine)) {
      val lineFormatted = line.trimStart()

      val greenTextStartIndex = findGreenTextStartSymbolIndex(lineFormatted)
      if (greenTextStartIndex >= 0) {
        processGreenText(postInlineQuoteColorUpdated, greenTextStartIndex, line, offset, commentTextAnnotatedBuilder)
      }

      processLinks(postLinkColorUpdated, line, offset, commentTextAnnotatedBuilder)
      processQuotes(postQuoteColorUpdated, postHighlightQuoteColorUpdated, line, offset, commentTextAnnotatedBuilder)
      processBoardLinks(postQuoteColorUpdated, line, offset, commentTextAnnotatedBuilder)
      processSpoilers(postSpoilerColor, postSpoilerRevealTextColor, line, offset, commentTextAnnotatedBuilder)

      offset += (line.length + newLineLen)
    }

    return commentTextAnnotatedBuilder.toAnnotatedString()
  }

  private fun processSpoilers(
    postSpoilerColor: Color,
    postSpoilerRevealTextColor: Color,
    line: String,
    offset: Int,
    commentTextAnnotatedBuilder: AnnotatedString.Builder
  ) {
    val closedSpoilerMatcher = CLOSED_SPOILER_PATTERN.matcher(line)
    while (closedSpoilerMatcher.find()) {
      val start = closedSpoilerMatcher.start(0) + offset
      val end = closedSpoilerMatcher.end(0) + offset

      commentTextAnnotatedBuilder.addStyle(SpanStyle(color = postSpoilerColor, background = postSpoilerColor), start, end)
    }

    val openedSpoilerMatcher = OPENED_SPOILER_PATTERN.matcher(line)
    while (openedSpoilerMatcher.find()) {
      val start = openedSpoilerMatcher.start(0) + offset
      val end = openedSpoilerMatcher.end(0) + offset

      commentTextAnnotatedBuilder.addStyle(SpanStyle(color = postSpoilerRevealTextColor, background = postSpoilerColor), start, end)
    }
  }

  private fun processGreenText(
    postInlineQuoteColor: Color,
    greenTextStartIndex: Int,
    line: String,
    offset: Int,
    commentTextAnnotatedBuilder: AnnotatedString.Builder
  ) {
    val start = greenTextStartIndex + offset
    val end = line.length + offset

    commentTextAnnotatedBuilder.addStyle(SpanStyle(color = postInlineQuoteColor), start, end)
  }

  private fun processLinks(
    postLinkColor: Color,
    line: String,
    offset: Int,
    commentTextAnnotatedBuilder: AnnotatedString.Builder
  ) {
    val matcher = URL_PATTERN.matcher(line)

    while (matcher.find()) {
      val start = matcher.start(0) + offset
      val end = matcher.end(0) + offset

      commentTextAnnotatedBuilder.addStyle(
        SpanStyle(color = postLinkColor, textDecoration = TextDecoration.Underline),
        start,
        end
      )
    }
  }

  private fun processBoardLinks(
    postQuoteColor: Color,
    line: String,
    offset: Int,
    commentTextAnnotatedBuilder: AnnotatedString.Builder
  ) {
    val matcher = BOARD_LINK_MATCHER.matcher(line)

    while (matcher.find()) {
      val start = matcher.start(0) + offset
      val end = matcher.end(0) + offset

      commentTextAnnotatedBuilder.addStyle(
        SpanStyle(color = postQuoteColor, textDecoration = TextDecoration.Underline),
        start,
        end
      )
    }
  }

  private fun processQuotes(
    postQuoteColor: Color,
    postHighlightQuoteColor: Color,
    line: String,
    offset: Int,
    commentTextAnnotatedBuilder: AnnotatedString.Builder
  ) {
    val matcher = QUOTE_MATCHER.matcher(line)

    while (matcher.find()) {
      val color = if (matcher.group(0).contains("11223344")) {
        postHighlightQuoteColor
      } else {
        postQuoteColor
      }

      val start = matcher.start(0) + offset
      val end = matcher.end(0) + offset

      commentTextAnnotatedBuilder.addStyle(
        SpanStyle(color = color, textDecoration = TextDecoration.Underline),
        start,
        end
      )
    }
  }

  private fun findGreenTextStartSymbolIndex(lineFormatted: String): Int {
    val curr = lineFormatted.getOrNull(0)
    val next = lineFormatted.getOrNull(1)

    if (curr == '>' && (next == null || next != '>')) {
      return 0
    }

    return -1
  }

}