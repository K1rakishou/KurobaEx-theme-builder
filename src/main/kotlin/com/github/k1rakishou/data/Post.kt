package com.github.k1rakishou.data

import androidx.compose.ui.text.AnnotatedString

data class Post(
  val postTitle: AnnotatedString,
  val postComment: AnnotatedString,
  val replies: AnnotatedString,
  val postImage: PostImage?,
  val savedReply: Boolean
)

data class PostImage(
  val size: Long = 303 * 1024L,
  val name: String = "sticky btfo",
  val extension: String = "PNG",
  val dimens: String = "535x420"
)