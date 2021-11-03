package com.github.k1rakishou.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import com.github.k1rakishou.LocalDeps
import com.github.k1rakishou.LocalKurobaTheme
import com.github.k1rakishou.data.Bookmark
import com.github.k1rakishou.data.BookmarkRepository

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarksScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val backColor by kurobaTheme.backColor

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(backColor)
  ) {
    Row(
      modifier = Modifier
        .fillMaxSize()
    ) {
      val lazyListState = rememberLazyListState()
      val bookmarks = remember { BookmarkRepository.bookmarks }

      LazyVerticalGrid(
        modifier = Modifier.weight(1f),
        cells = GridCells.Adaptive(minSize = 180.dp),
        state = lazyListState,
        content = {
          items(count = bookmarks.size) { index ->
            val bookmark = bookmarks.get(index)
            BookmarkItem(bookmark)
          }
        }
      )

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
        adapter = rememberScrollbarAdapter(lazyListState)
      )
    }
  }
}

@Composable
private fun BookmarkItem(bookmark: Bookmark) {
  val kurobaTheme = LocalKurobaTheme.current
  val deps = LocalDeps.current

  val accentColor by kurobaTheme.accentColor
  val bookmarkCounterNotWatchingColor by kurobaTheme.bookmarkCounterNotWatchingColor
  val bookmarkCounterHasRepliesColor by kurobaTheme.bookmarkCounterHasRepliesColor
  val bookmarkCounterNormalColor by kurobaTheme.bookmarkCounterNormalColor
  var selected by remember { mutableStateOf(false) }

  val textColor = if (bookmark.hasReplies) {
    bookmarkCounterHasRepliesColor
  } else if (!bookmark.watching) {
    bookmarkCounterNotWatchingColor
  } else {
    bookmarkCounterNormalColor
  }

  val bgColorModifier = if (selected) {
    Modifier.background(accentColor.copy(alpha = 0.35f))
  } else {
    Modifier
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .then(bgColorModifier)
      .clickable { selected = !selected }
      .padding(horizontal = 4.dp, vertical = 8.dp)
  ) {
    Image(
      modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
      bitmap = deps.image,
      contentDescription = null
    )

    Spacer(modifier = Modifier.height(4.dp))

    val title = remember(key1 = textColor, key2 = bookmark.title) {
      AnnotatedString(bookmark.title, SpanStyle(color = textColor))
    }

    Text(
      modifier = Modifier.align(Alignment.CenterHorizontally),
      text = title
    )

    Spacer(modifier = Modifier.height(2.dp))

    val bookmarkInfo = remember(textColor, bookmark.totalPosts, bookmark.unreadPosts) {
      AnnotatedString("${bookmark.unreadPosts} / ${bookmark.totalPosts}", SpanStyle(color = textColor))
    }

    Text(
      modifier = Modifier.align(Alignment.CenterHorizontally),
      text = bookmarkInfo
    )
  }
}
