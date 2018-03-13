package ui.primitives.platform

import ui.primitives.Window
import ui.primitives.View
import kotlinx.cinterop.*
import platform.Foundation.*
import platform.UIKit.*

typealias WindowCallback = (PlatformWindow) -> Unit

val Platform = "ios"
var uglyHackToStoreARef: StableRef<PlatformApp>? = null

open class PlatformView {
  // non-final Kotlin subclasses of Objective-C classes are not yet supported
  // so we store it inside
  var uiview: UIView? = null
}

open class PlatformButton(title: String): View() {

  init {
    val button = UIButton()
    button.setTitle(title, 0)
    button.setTitleColor(UIColor.blueColor(), 0)
    button.setFrame(platform.CoreGraphics.CGRectMake(100.0, 100.0, 100.0, 100.0))
    uiview = button
  }
}

class AppDelegate : UIResponder(), UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta {}

    override fun init() = initBy(AppDelegate())
    override fun application(application: UIApplication, didFinishLaunchingWithOptions: NSDictionary?): Boolean {
      val platformWindow = PlatformWindow()
      this.window = platformWindow.window
      val app = uglyHackToStoreARef?.get()
      if (app != null) {
        app.windowCallback(platformWindow)
      }
      return true
    }

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) { _window = window }
}


open class PlatformApp(args: Array<String>) {
  val stableRef = StableRef.create(this)
  var args: Array<String> = args
  /* lateinit var gtkApplicationPointer: CPointer<GtkApplication>  */
  lateinit var windowCallback: WindowCallback

  fun run(windowCallback: WindowCallback): Int {
    this.windowCallback = windowCallback
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("konan") + args).map { it.cstr.getPointer(memScope) }.toCValues()
        uglyHackToStoreARef = stableRef
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(AppDelegate))
        }
    }
    return 1 // ios apps never return exit codes
  }


  fun free() {
     stableRef.dispose()
  }
}
