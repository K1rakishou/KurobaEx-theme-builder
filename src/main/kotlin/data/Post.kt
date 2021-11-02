package data

import androidx.compose.ui.text.AnnotatedString

data class Post(
  val postTitle: AnnotatedString,
  val postComment: AnnotatedString,
  val savedReply: Boolean
)