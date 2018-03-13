@file:Suppress("UNUSED_VARIABLE", "UNUSED_EXPRESSION")

package ui.primitives.platform
import kotlinx.cinterop.*
import gtk3.*
import ui.primitives.Window

typealias WindowCallback = (PlatformWindow) -> Unit
val Platform = "linux"

class ApplicationWindow(application: PlatformApp): PlatformWindow() {
  init {
    widgetPointer = gtk_application_window_new(application.gtkApplicationPointer)
  }
}

fun activate(@Suppress("UNUSED_PARAMETER") appPointer: CPointer<GtkWidget>?, userdata: gpointer?) {

    if (userdata != null) {
        val app = userdata.asStableRef<PlatformApp>().get()
        val window = ApplicationWindow(app)
        app.windowCallback(window)
        window.showAll()
    } else {
      println("no uderData passed")
    }
}

open class PlatformApp(args: Array<String>) {
  val stableRef = StableRef.create(this)
  var args: Array<String> = args
  lateinit var gtkApplicationPointer: CPointer<GtkApplication>
  lateinit var windowCallback: WindowCallback

  fun run(windowCallback: WindowCallback): Int {
    this.windowCallback = windowCallback
    gtkApplicationPointer = gtk_application_new("org.kotlin.native.gtk.example", G_APPLICATION_FLAGS_NONE)!!


    connectSignal(gtkApplicationPointer, "activate", staticCFunction(::activate), stableRef.asCPointer())

    val status = memScoped {
        g_application_run(gtkApplicationPointer.reinterpret(),
                args.size, args.map { it.cstr.getPointer(memScope) }.toCValues())
    }
    g_object_unref(gtkApplicationPointer)
    println("exit status: $status")
    return status
  }

  fun free() {
     stableRef.dispose()
  }
}
