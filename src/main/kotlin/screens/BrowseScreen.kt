package screens

import KurobaComposeDivider
import LocalKurobaTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.Post
import data.PostRepository

@Composable
fun BrowseScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val backColor by kurobaTheme.backColor
  val textColorPrimary by kurobaTheme.textColorPrimary
  val accentColor by kurobaTheme.accentColor
  val postInlineQuoteColor by kurobaTheme.postInlineQuoteColor
  val postLinkColor by kurobaTheme.postLinkColor
  val postQuoteColor by kurobaTheme.postQuoteColor
  val postSubjectColor by kurobaTheme.postSubjectColor
  val textColorHint by kurobaTheme.textColorHint
  val postSpoilerColor by kurobaTheme.postSpoilerColor
  val postSpoilerRevealTextColor by kurobaTheme.postSpoilerRevealTextColor

  Row(
    modifier = Modifier
      .fillMaxSize()
      .background(backColor)
  ) {
    val scrollState = rememberScrollState()

    Column(
      modifier = Modifier
        .weight(1f)
        .verticalScroll(state = scrollState)
    ) {
      val posts = remember(
        textColorPrimary,
        accentColor,
        postInlineQuoteColor,
        postLinkColor,
        postQuoteColor,
        postSubjectColor,
        textColorHint,
        postSpoilerColor,
        postSpoilerRevealTextColor
      ) {
        PostRepository.getPosts(
          textColorPrimary = textColorPrimary,
          accentColor = accentColor,
          postInlineQuoteColor = postInlineQuoteColor,
          postLinkColor = postLinkColor,
          postQuoteColor = postQuoteColor,
          postSubjectColor = postSubjectColor,
          textColorHint = textColorHint,
          postSpoilerColor = postSpoilerColor,
          postSpoilerRevealTextColor = postSpoilerRevealTextColor
        )
      }

      for ((index, post) in posts.withIndex()) {
        PostItem(post)

        if (index < posts.lastIndex) {
          Spacer(modifier = Modifier.height(8.dp))

          KurobaComposeDivider(
            modifier = Modifier
              .height(1.dp)
              .fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(8.dp))
        }
      }
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
private fun PostItem(
  post: Post
) {
  val kurobaTheme = LocalKurobaTheme.current
  val postHighlightedColor by kurobaTheme.postHighlightedColor
  var selected by remember { mutableStateOf(false) }

  val selectedBgModifier = if (selected) {
    Modifier.background(postHighlightedColor)
  } else {
    Modifier
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(IntrinsicSize.Min)
      .clickable { selected = !selected }
      .then(selectedBgModifier)
  ) {
    val bgColorState = if (post.savedReply) {
      kurobaTheme.postSavedReplyColor
    } else {
      kurobaTheme.postUnseenLabelColor
    }

    val bgColor by bgColorState

    Spacer(
      modifier = Modifier
        .width(12.dp)
        .fillMaxHeight()
        .padding(horizontal = 2.dp)
        .background(bgColor)
    )

    Column(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
      Text(text = post.postTitle)
      Text(text = post.postComment)
    }
  }
}