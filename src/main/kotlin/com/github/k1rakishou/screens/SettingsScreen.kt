package com.github.k1rakishou.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.k1rakishou.KurobaComposeDivider
import com.github.k1rakishou.KurobaComposeSwitch
import com.github.k1rakishou.LocalKurobaTheme

@Composable
fun SettingsScreen() {
  val kurobaTheme = LocalKurobaTheme.current
  val backColor by kurobaTheme.backColor

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(backColor)
      .verticalScroll(state = rememberScrollState())
  ) {
    (0..50).forEach { index ->
      when (index % 2) {
        0 -> LinkSettingItem(index)
        1 -> BooleanSettingItem(index)
      }

      KurobaComposeDivider(modifier = Modifier.height(1.dp).fillMaxWidth())
    }
  }
}

@Composable
private fun BooleanSettingItem(index: Int) {
  val kurobaTheme = LocalKurobaTheme.current

  Row(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth(1f)
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .height(52.dp)
        .clickable { }
    ) {

      val textColorPrimary by kurobaTheme.textColorPrimary
      val textColorSecondary by kurobaTheme.textColorSecondary

      Text(
        modifier = Modifier
          .fillMaxWidth()
          .weight(0.5f),
        fontSize = 16.sp,
        text = "Setting title $index",
        color = textColorPrimary
      )
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .weight(0.5f),
        fontSize = 16.sp,
        text = "Setting subtitle",
        color = textColorSecondary
      )
    }

    var checked by remember { mutableStateOf(index % 3 == 0) }

    KurobaComposeSwitch(
      initiallyChecked = checked,
      onCheckedChange = { nowChecked -> checked = nowChecked }
    )
  }
}

@Composable
private fun LinkSettingItem(index: Int) {
  val kurobaTheme = LocalKurobaTheme.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .height(52.dp)
      .clickable { }
  ) {
    val textColorPrimary by kurobaTheme.textColorPrimary
    val textColorSecondary by kurobaTheme.textColorSecondary

    Text(
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.5f),
      fontSize = 16.sp,
      text = "Setting title $index",
      color = textColorPrimary
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .weight(0.5f),
      fontSize = 16.sp,
      text = "Setting subtitle",
      color = textColorSecondary
    )
  }
}