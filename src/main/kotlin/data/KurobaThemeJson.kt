package data

import com.google.gson.annotations.SerializedName

data class KurobaThemeJson(
  @SerializedName("name")
  val name: String,
  @SerializedName("is_light_theme")
  val isLightTheme: Boolean,
  @SerializedName("light_status_bar")
  val lightStatusBar: Boolean,
  @SerializedName("light_nav_bar")
  val lightNavBar: Boolean,
  @SerializedName("accent_color")
  val accentColor: String? = null,
  @SerializedName("primary_color")
  val primaryColor: String? = null,
  @SerializedName("back_color")
  val backColor: String? = null,
  @SerializedName("back_color_secondary")
  val backColorSecondary: String? = null,
  @SerializedName("error_color")
  val errorColor: String? = null,
  @SerializedName("text_color_primary")
  val textColorPrimary: String? = null,
  @SerializedName("text_color_secondary")
  val textColorSecondary: String? = null,
  @SerializedName("text_color_hint")
  val textColorHint: String? = null,
  @SerializedName("post_highlighted_color")
  val postHighlightedColor: String? = null,
  @SerializedName("post_saved_reply_color")
  val postSavedReplyColor: String? = null,
  @SerializedName("post_subject_color")
  val postSubjectColor: String? = null,
  @SerializedName("post_details_color")
  val postDetailsColor: String? = null,
  @SerializedName("post_name_color")
  val postNameColor: String? = null,
  @SerializedName("post_inline_quote_color")
  val postInlineQuoteColor: String? = null,
  @SerializedName("post_quote_color")
  val postQuoteColor: String? = null,
  @SerializedName("post_highlight_quote_color")
  val postHighlightQuoteColor: String? = null,
  @SerializedName("post_link_color")
  val postLinkColor: String? = null,
  @SerializedName("post_spoiler_color")
  val postSpoilerColor: String? = null,
  @SerializedName("post_spoiler_reveal_text_color")
  val postSpoilerRevealTextColor: String? = null,
  @SerializedName("post_unseen_label_color")
  val postUnseenLabelColor: String? = null,
  @SerializedName("divider_color")
  val dividerColor: String? = null,
  @SerializedName("bookmark_counter_not_watching_color")
  val bookmarkCounterNotWatchingColor: String? = null,
  @SerializedName("bookmark_counter_has_replies_color")
  val bookmarkCounterHasRepliesColor: String? = null,
  @SerializedName("bookmark_counter_normal_color")
  val bookmarkCounterNormalColor: String? = null
)