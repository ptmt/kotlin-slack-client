package ui.primitives.platform
import kotlinx.cinterop.*
import gtk3.*

open class PlatformView(): Widget() {
    var widgets: MutableList<Widget> = mutableListOf<Widget>()

    fun add(widget: Widget) {
        widgets.add(widget)
        widget.parentWidget = this
        gtk_container_add(this.widgetPointer?.reinterpret(), widget.widgetPointer)
    }

    fun remove(widget: Widget) {
        val index = widgets.indexOf(widget)
        if (index > -1) {
            gtk_container_remove(this.widgetPointer?.reinterpret(), widget.widgetPointer)
            widgets.removeAt(index)
            widget.parentWidget = null
        }
    }
}
