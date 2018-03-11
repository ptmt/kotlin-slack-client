import ui.primitives.*

/*
 * Entry point
 */
fun main(args: Array<String>) {
    var app = App(args)
    app.run { window ->
      window.setFrame(WindowFrame(0.0, 0.0, 700.0, 700.0))
      window.title = "Kotlin Slack Client"
    }
    app.free()
}
