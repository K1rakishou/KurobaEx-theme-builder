package data

import kotlin.random.Random

object BookmarkRepository {
  private val random = Random(0)

  val bookmarks by lazy {
    return@lazy (0..100).map { index ->
      val totalPosts = random.nextInt(1, 1000)
      val unreadPosts = random.nextInt(0, totalPosts)
      val hasReplies = random.nextInt() % 5 == 0

      return@map Bookmark(
        watching = index % 2 == 0,
        title = "Bookmark title ${index}",
        totalPosts = totalPosts,
        unreadPosts = unreadPosts,
        hasReplies = hasReplies
      )
    }
  }

}