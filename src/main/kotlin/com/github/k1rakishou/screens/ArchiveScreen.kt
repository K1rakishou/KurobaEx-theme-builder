package com.github.k1rakishou.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.github.k1rakishou.LocalDeps
import com.github.k1rakishou.LocalKurobaTheme
import com.github.k1rakishou.data.Archive
import com.github.k1rakishou.data.ArchiveRepository

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArchiveScreen() {
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
      val archives = remember { ArchiveRepository.archives }

      LazyVerticalGrid(
        modifier = Modifier.weight(1f),
        cells = GridCells.Adaptive(minSize = 340.dp),
        state = lazyListState,
        content = {
          items(count = archives.size) { index ->
            val archive = archives.get(index)
            ArchiveItem(archive)
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
private fun ArchiveItem(archive: Archive) {
  val kurobaTheme = LocalKurobaTheme.current
  val deps = LocalDeps.current
  val backColorSecondary by kurobaTheme.backColorSecondary
  val postSubjectColor by kurobaTheme.postSubjectColor
  val textColorPrimary by kurobaTheme.textColorPrimary
  val textColorHint by kurobaTheme.textColorHint
  var running by remember { mutableStateOf(true) }

  Card(
    modifier = Modifier
      .wrapContentSize()
      .padding(4.dp)
      .clickable { running = !running },
    backgroundColor = backColorSecondary
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(2.dp)
    ) {
      val alpha = if (running) {
        1f
      } else {
        .5f
      }

      val title = remember(postSubjectColor, archive) {
        AnnotatedString(archive.title, SpanStyle(color = postSubjectColor))
      }
      Text(modifier = Modifier.alpha(alpha), text = title)

      Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
        Spacer(modifier = Modifier.width(4.dp))

        Image(
          modifier = Modifier
            .width(100.dp)
            .height(140.dp)
            .alpha(alpha),
          bitmap = deps.image,
          contentDescription = null
        )

        Spacer(modifier = Modifier.width(4.dp))

        val status = remember(textColorPrimary, archive) {
          buildAnnotatedString {
            append(AnnotatedString("Thread No. ${archive.threadNo}", SpanStyle(color = textColorPrimary)))
            append("\n")
            append(AnnotatedString("Status: Downloading", SpanStyle(color = textColorPrimary)))
            append("\n")
            append(AnnotatedString("Started: ${archive.started}", SpanStyle(color = textColorPrimary)))
            append("\n")
            append(AnnotatedString("Updated: ${archive.updated}", SpanStyle(color = textColorPrimary)))
          }
        }
        Text(modifier = Modifier.align(Alignment.CenterVertically).alpha(alpha), text = status)
      }

      val footer = remember(textColorHint, archive) {
        buildAnnotatedString {
          append(
            AnnotatedString(
              "Posts ${archive.postsCount}, Media: ${archive.mediaCount}, Media on disk: ${archive.mediaOnDiskSize}",
              SpanStyle(color = textColorHint)
            )
          )
        }
      }
      Text(modifier = Modifier.alpha(alpha), text = footer)
    }
  }

}
