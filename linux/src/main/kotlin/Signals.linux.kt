package ui.primitives.platform
import kotlinx.cinterop.*
import gtk3.*


enum class ConnectFlags {
    After, Swapped;

    fun toGConnectFlags(): GConnectFlags {
        if (this == After) {
          return G_CONNECT_AFTER
        } else {
          return G_CONNECT_SWAPPED
        }
    }
}

// Note that all callback parameters must be primitive types or nullable C pointers.
fun <F : CFunction<*>> connectSignal(
  instance: CPointer<*>,
  name: String,
  handler: CPointer<F>,
  data: gpointer? = null,
  connectFlags: ConnectFlags = ConnectFlags.After): Long
{
    return g_signal_connect_data(instance.reinterpret(), name, handler.reinterpret(),
            data = data, destroy_data = null, connect_flags = connectFlags.toGConnectFlags())

}

fun disconnectSignal(instance: CPointer<*>, handlerId: Long) {
    g_signal_handler_disconnect(instance, handlerId)
}
