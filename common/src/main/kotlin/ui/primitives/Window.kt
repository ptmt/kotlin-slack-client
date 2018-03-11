package ui.primitives

import ui.primitives.platform.PlatformWindow

data class WindowFrame(val x: Double, val y: Double, val width: Double, val height: Double)

class Window(): PlatformWindow() {
}
