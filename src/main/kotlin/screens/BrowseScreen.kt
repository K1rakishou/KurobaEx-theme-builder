package screens

import KurobaComposeDivider
import LocalDeps
import LocalKurobaTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import data.Post
import data.PostRepository

@Composable
fun BrowseScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val accentColor by kurobaTheme.accentColor
  val backColor by kurobaTheme.backColor
  val textColorPrimary by kurobaTheme.textColorPrimary
  val textColorSecondary by kurobaTheme.textColorSecondary
  val postInlineQuoteColor by kurobaTheme.postInlineQuoteColor
  val postLinkColor by kurobaTheme.postLinkColor
  val postQuoteColor by kurobaTheme.postQuoteColor
  val postSubjectColor by kurobaTheme.postSubjectColor
  val textColorHint by kurobaTheme.textColorHint
  val postSpoilerColor by kurobaTheme.postSpoilerColor
  val postSpoilerRevealTextColor by kurobaTheme.postSpoilerRevealTextColor
  val postNameColor by kurobaTheme.postNameColor

  Box(modifier = Modifier.background(backColor)) {
    Row(
      modifier = Modifier
        .fillMaxSize()
    ) {
      val scrollState = rememberScrollState()

      Column(
        modifier = Modifier
          .weight(1f)
          .verticalScroll(state = scrollState)
      ) {
        val posts = remember(
          textColorPrimary,
          textColorSecondary,
          accentColor,
          postInlineQuoteColor,
          postLinkColor,
          postQuoteColor,
          postSubjectColor,
          textColorHint,
          postSpoilerColor,
          postSpoilerRevealTextColor,
          postNameColor
        ) {
          PostRepository.getPosts(
            textColorPrimary = textColorPrimary,
            textColorSecondary = textColorSecondary,
            accentColor = accentColor,
            postInlineQuoteColor = postInlineQuoteColor,
            postLinkColor = postLinkColor,
            postQuoteColor = postQuoteColor,
            postSubjectColor = postSubjectColor,
            textColorHint = textColorHint,
            postSpoilerColor = postSpoilerColor,
            postSpoilerRevealTextColor = postSpoilerRevealTextColor,
            postNameColor = postNameColor,
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

    FloatingActionButton(
      modifier = Modifier
        .size(64.dp)
        .align(Alignment.BottomEnd)
        .offset(x = (-24).dp, y = (-16).dp),
      backgroundColor = accentColor,
      contentColor = Color.White,
      onClick = {}
    ) {
      Image(
        modifier = Modifier
          .fillMaxSize()
          .padding(12.dp),
        imageVector = Icons.Filled.Create,
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = null
      )
    }
  }

}

@Composable
private fun PostItem(
  post: Post
) {
  val kurobaTheme = LocalKurobaTheme.current
  val deps = LocalDeps.current
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
      .padding(vertical = 4.dp)
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

    if (post.postImage != null) {
      Spacer(modifier = Modifier.width(4.dp))

      Image(
        modifier = Modifier
          .width(160.dp)
          .height(120.dp),
        bitmap = deps.image,
        contentDescription = null
      )

      Spacer(modifier = Modifier.width(4.dp))
    }

    Column(modifier = Modifier.wrapContentHeight().fillMaxWidth()) {
      Text(text = post.postTitle)
      Text(text = post.postComment)

      Spacer(modifier = Modifier.height(6.dp))
      Text(text = post.replies)
    }
  }
}