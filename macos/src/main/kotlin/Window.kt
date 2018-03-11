import kotlinx.cinterop.*
import platform.AppKit.*
import platform.Foundation.*
import platform.objc.*
import platform.osx.*

fun main(args: Array<String>) {
    autoreleasepool {
        runApp()
    }
}

private fun runApp() {
    val app = NSApplication.sharedApplication()

    app.delegate = MyAppDelegate()
    app.setActivationPolicy(NSApplicationActivationPolicy.NSApplicationActivationPolicyRegular)
    app.activateIgnoringOtherApps(true)

    app.run()
}

private class MyAppDelegate() : NSObject(), NSApplicationDelegateProtocol {

    private val window: NSWindow

    init {
        val mainDisplayRect = NSScreen.mainScreen()!!.frame
        val windowRect = mainDisplayRect.useContents {
            NSMakeRect(
                    100.0,
                    100.0,
                    size.width * 0.85,
                    size.height * 0.85
            )
        }

        val windowStyle = NSWindowStyleMaskTitled or NSWindowStyleMaskMiniaturizable or
                NSWindowStyleMaskClosable or NSWindowStyleMaskResizable

        window = NSWindow(windowRect, windowStyle, NSBackingStoreBuffered, false).apply {
            titleVisibility = NSWindowTitleHidden
            titlebarAppearsTransparent = true
            opaque = true
            hasShadow = true
            preferredBackingLocation = NSWindowBackingLocationVideoMemory
            hidesOnDeactivate = false
            backgroundColor = NSColor.whiteColor()
            releasedWhenClosed = false

            delegate = object : NSObject(), NSWindowDelegateProtocol {
                override fun windowShouldClose(sender: NSWindow): Boolean {
                    NSApplication.sharedApplication().stop(this)
                    return true
                }
            }
        }
    }

    override fun applicationWillFinishLaunching(notification: NSNotification) {
        window.makeKeyAndOrderFront(this)
    }
}
