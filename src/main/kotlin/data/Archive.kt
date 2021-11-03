package data

data class Archive(
  val title: String,
  val threadNo: Long,
  val started: String,
  val updated: String,
  val postsCount: Int,
  val mediaCount: Int,
  val mediaOnDiskSize: String,
)