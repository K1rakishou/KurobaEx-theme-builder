import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun KurobaComposeSwitch(
  modifier: Modifier = Modifier,
  initiallyChecked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  val chanTheme = LocalKurobaTheme.current

  Switch(
    checked = initiallyChecked,
    onCheckedChange = onCheckedChange,
    colors = chanTheme.switchColors(),
    modifier = modifier
  )
}

@Composable
fun KurobaComposeDivider(
  modifier: Modifier = Modifier,
) {
  val chanTheme = LocalKurobaTheme.current
  val dividerColor by chanTheme.dividerColor

  Divider(
    modifier = modifier,
    color = dividerColor
  )
}