import androidx.compose.runtime.mutableStateListOf
import com.google.gson.Gson
import screens.popup.PopupScreen

object Deps {
  private val _popupScreensStack = mutableStateListOf<PopupScreen>()
  val popupScreensStack: List<PopupScreen>
    get() = _popupScreensStack

  fun popPopupScreen(popupScreen: PopupScreen) {
    if (_popupScreensStack.isEmpty()) {
      return
    }

    if (_popupScreensStack.lastOrNull() != popupScreen) {
      return
    }

    _popupScreensStack.removeLastOrNull()
  }

  fun pushPopupScreen(popupScreen: PopupScreen) {
    if (_popupScreensStack.contains(popupScreen)) {
      return
    }

    _popupScreensStack.add(popupScreen)
  }

  val gson = Gson().newBuilder().create()
  val gsonPrettyPrint by lazy { gson.newBuilder().setPrettyPrinting().create() }
}