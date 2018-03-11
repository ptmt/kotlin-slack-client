package ui.primitives.platform
import kotlinx.cinterop.*
import gtk3.*
import ui.primitives.WindowFrame
import ui.primitives.View

open class PlatformWindow(): PlatformView() {
  var title: String = ""
    set(title) {
      gtk_window_set_title(this.widgetPointer?.reinterpret(), title)
    }

  fun setFrame(frame: WindowFrame) {
      gtk_window_set_default_size(
        this.widgetPointer?.reinterpret(),
        frame.width.toInt(),
        frame.height.toInt())
  }

  fun <T: View> addSubview(view: T) {
    add(view)
  }
}
