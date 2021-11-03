package com.github.k1rakishou.data

import com.github.k1rakishou.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

object ArchiveRepository {
  private val random = Random(0)
  private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

  val archives by lazy {
    return@lazy (0..50).map { index ->
      return@map Archive(
        title = "Thread archive $index",
        threadNo = random.nextLong(1000000L, 99999999L),
        started = dateFormat.format(Date()),
        updated = dateFormat.format(Date()),
        postsCount = random.nextInt(1, 999),
        mediaCount = random.nextInt(1, 100),
        mediaOnDiskSize = Utils.getReadableFileSize(random.nextLong(100000L, 9999999L))
      )
    }
  }
}