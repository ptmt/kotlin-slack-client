package ui.primitives.platform
import kotlinx.cinterop.*
import gtk3.*
import ui.primitives.View

open class PlatformButton(title: String): View() {
  init {
    this.widgetPointer = gtk_button_new_with_label(title)
  }
}
