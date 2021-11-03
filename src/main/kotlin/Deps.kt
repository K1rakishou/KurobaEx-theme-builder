import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import com.google.gson.Gson
import org.jetbrains.skia.Image
import screens.popup.PopupScreen

object Deps {
  private val _popupScreensStack = mutableStateListOf<PopupScreen>()
  val popupScreensStack: List<PopupScreen>
    get() = _popupScreensStack

  val image: ImageBitmap by lazy {
    return@lazy Image.makeFromEncoded(
        this.javaClass.getResourceAsStream("post_image.png")!!.readAllBytes()
    ).toComposeImageBitmap()
  }

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