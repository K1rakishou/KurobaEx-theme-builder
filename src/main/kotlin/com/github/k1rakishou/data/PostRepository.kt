package com.github.k1rakishou.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import com.github.k1rakishou.Utils
import java.text.SimpleDateFormat
import java.util.*


object PostRepository {
  private val comments = mutableListOf<String>()
  private val subjects = mutableListOf<String>()

  private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

  init {
    comments.add("""
      >New to Mahjong? Play here (tutorial included):
      https://mahjongsoul.game.yo-star.com

      >Interested in this general or in Mahjong? READ THIS:
      https://repo.riichi.moe

      >Watch M-League (Monday, Tuesday, Thursday, Friday 19:00 JST):
      https://abema.tv/now-on-air/mahjong
      https://abema.tv/video/genre/mahjong

      >/mjg/ booru:
      https://mjg.booru.org/index.php

      >Washizu mahjong team tourney
      https://docs.google.com/document/d/1hONuIxd3kQMZUX2kY2xpM6nkLb3PDrFNIqgz2KBq8RQ
      >sign-ups
      https://docs.google.com/forms/d/1iVPhaR0RxF621eEY8e3PSEGECdKi_BAEu7n7zMhuMrw/viewform?edit_requested=true
      
      _Closed spoiler_
      _Opened spoiler_

      >World Championship
      https://docs.google.com/spreadsheets/d/1h1aXl4NjwAUaPzmdqIToK9-qvmW8F7HXslfFF0UAJYk/edit#gid=0
      >sign-ups
      https://docs.google.com/forms/d/e/1FAIpQLSdLFThiw-mRqhCe0DGtmH3mbOIVz8-jeIviLX5Wox9r_V3eDg/viewform
      
      >>12345678 (postQuoteColor)
      >>11223344 (postHighlightQuoteColor)
    """.trimIndent())
    subjects.add("/mjg/ - Mahjong General ")

    comments.add("""
      This general is for the discussion of English-translated Japanese visual novels.
      All posting of untranslated visual novels belongs on >>>/jp/
      E-celeb shitposting is not allowed.
      Kindly use spoiler tags appropriately when discussing plot spoilers to facilitate smooth discussion.

      >Having trouble with your VN? Try the following before you ask for tech support:
      1. Be in Japanese locale
      2. Read the Readme
      3. Check this https://pastebin.com/BVRT6tSN
      4. Copy error messages with CTRL+C and paste them with CTRL+V into DeepL
      5. Google it

      >FAQs, Recommendations, and Other Useful Things:
      https://sites.google.com/view/moechart/
      https://files.catbox.moe/143by7.png
      https://i.imgur.com/3CDmFQm.jpg
      https://www.mediafire.com/file/ov74ltf4cgpji59/japReader-1.2.zip/file

      >Need a novel with a specific element?
      http://vndb.org/g

      >Download Links:
      https://pastebin.com/YTGdpqZL

      Previous thread: >>358195254
    """.trimIndent())
    subjects.add("/vn/ - Visual Novel General #4554")
  }

  fun getPosts(
    textColorPrimary: Color,
    textColorSecondary: Color,
    accentColor: Color,
    postInlineQuoteColor: Color,
    postLinkColor: Color,
    postQuoteColor: Color,
    postHighlightQuoteColor: Color,
    postSubjectColor: Color,
    postDetailsColor: Color,
    postSpoilerColor: Color,
    postSpoilerRevealTextColor: Color,
    postNameColor: Color
  ): List<Post> {
    return processComments(
      textColorPrimary = textColorPrimary,
      accentColor = accentColor,
      postInlineQuoteColor = postInlineQuoteColor,
      postLinkColor = postLinkColor,
      postQuoteColor = postQuoteColor,
      postHighlightQuoteColor = postHighlightQuoteColor,
      postSpoilerColor = postSpoilerColor,
      postSpoilerRevealTextColor = postSpoilerRevealTextColor,
    ).mapIndexed { index, postComment ->
      val postImage = if (index % 2 == 0) PostImage() else null

      return@mapIndexed Post(
        postTitle = processTitle(
          index = index,
          postImage = postImage,
          postSubjectColor = postSubjectColor,
          postDetailsColor = postDetailsColor,
          postNameColor = postNameColor
        ),
        postComment = postComment,
        replies = AnnotatedString(
          text = "${(index + 1) * 100} replies",
          spanStyle = SpanStyle(color = textColorSecondary)
        ),
        savedReply = index % 2 == 0,
        postImage = postImage
      )
    }
  }

  private fun processTitle(
    index: Int,
    postImage: PostImage?,
    postSubjectColor: Color,
    postDetailsColor: Color,
    postNameColor: Color
  ): AnnotatedString {
    val postNo = (index + 1000000).toLong()
    val builder = AnnotatedString.Builder()

    builder.append(
      AnnotatedString(
        subjects.getOrNull(index) ?: "Thread subject ${index}",
        SpanStyle(color = postSubjectColor)
      )
    )

    builder.append('\n')

    val name =  if (index % 2 == 0) {
      "Tripcode !!ABCDEFGHKlmnOP"
    } else {
      "Anonymous"
    }

    builder.append(
      AnnotatedString(name, SpanStyle(color = postNameColor))
    )
    builder.append(' ')

    val currentTime = dateFormat.format(Date())
    builder.append(
      AnnotatedString("No. ${postNo} ${currentTime}", SpanStyle(color = postDetailsColor))
    )

    if (postImage != null) {
      builder.append('\n')

      builder.append(
        AnnotatedString(
          "${postImage.name} ${postImage.extension} ${postImage.dimens} ${Utils.getReadableFileSize(postImage.size)}",
          SpanStyle(color = postDetailsColor)
        )
      )
    }

    return builder.toAnnotatedString()
  }

  private fun processComments(
    textColorPrimary: Color,
    accentColor: Color,
    postInlineQuoteColor: Color,
    postLinkColor: Color,
    postQuoteColor: Color,
    postHighlightQuoteColor: Color,
    postSpoilerColor: Color,
    postSpoilerRevealTextColor: Color,
  ): List<AnnotatedString> {
    return comments.map { comment ->
      ReplyCommentHelper.processReplyCommentHighlight(
        commentText = comment,
        textColorPrimary = textColorPrimary,
        accentColor = accentColor,
        postInlineQuoteColor = postInlineQuoteColor,
        postLinkColor = postLinkColor,
        postQuoteColor = postQuoteColor,
        postHighlightQuoteColor = postHighlightQuoteColor,
        postSpoilerColor = postSpoilerColor,
        postSpoilerRevealTextColor = postSpoilerRevealTextColor
      )
    }
  }

}