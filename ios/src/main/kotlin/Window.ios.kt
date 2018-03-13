package ui.primitives.platform
import kotlinx.cinterop.*
import platform.UIKit.*
import ui.primitives.WindowFrame
import ui.primitives.View

open class PlatformWindow() {
  var window: UIWindow = UIWindow(frame = UIScreen.mainScreen().bounds)
  var title: String = "" //unsupported on ios

  init {
    window.backgroundColor = UIColor.whiteColor()
    val vc = UIViewController()
    val rootView = UIView(UIScreen.mainScreen().bounds)
    vc.view = rootView
    window.rootViewController = vc;
    window.makeKeyAndVisible()
  }

  fun setFrame(frame: WindowFrame) {
    // makes no sense for the main window
  }

  fun <T: View> addSubview(view: T) {
    // println(window.rootViewController!!.view)
    window.rootViewController?.view?.addSubview(view.uiview!!)
  }
}
