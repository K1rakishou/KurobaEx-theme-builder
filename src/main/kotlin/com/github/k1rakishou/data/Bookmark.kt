package com.github.k1rakishou.data

data class Bookmark(
  val watching: Boolean,
  val title: String,
  val totalPosts: Int,
  val unreadPosts: Int,
  val hasReplies: Boolean
)