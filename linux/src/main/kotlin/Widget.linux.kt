package ui.primitives.platform;
import kotlinx.cinterop.*
import gtk3.*

open class Widget {
    private var signals: List<Pair<Long, Any>> = emptyList()
    var widgetPointer: CPointer<GtkWidget>? = null

    var parentWidget: Widget?
        get() = this
        set(value) {
            if (parentWidget != null) {
                didMoveToParent()
            } else {
                didMoveFromParent()
                removeSignals()
            }
        }

    var opacity: Double
        get {
            return gtk_widget_get_opacity(this.widgetPointer)
        }
        set(newValue) {
            gtk_widget_set_opacity(this.widgetPointer?.reinterpret(), newValue)
        }

    fun free() {
        removeSignals()
    }

    private fun removeSignals() {
        for ((handlerId, _) in signals) {
            val widget = widgetPointer
            if (widget != null) {
                disconnectSignal(instance = widget, handlerId = handlerId)
            }
        }

        signals = emptyList()
    }

    fun didMoveToParent() {

    }

    fun didMoveFromParent() {

    }

    fun showAll() {
        gtk_widget_show_all(widgetPointer)
    }

    fun showNow() {
        gtk_widget_show_now(widgetPointer)
    }

    fun show() {
        gtk_widget_show(widgetPointer)
    }

    fun hide() {
        gtk_widget_hide(widgetPointer)
    }
}
