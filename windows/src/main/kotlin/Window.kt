import kotlinx.cinterop.*
import platform.windows.*

fun main(args: Array<String>): Int {
  var hinst: HINSTANCE = CPointer()

  // Create the main window.
  // docs: https://msdn.microsoft.com/en-us/library/windows/desktop/ms632680(v=vs.85).aspx

  var hwndMain: HWND = CreateWindowEx(
      0,                      // no extended styles
      "MainWClass",           // class name
      "Main Window",          // window name
      WS_OVERLAPPEDWINDOW or   // overlapped window
               WS_HSCROLL or   // horizontal scroll bar
               WS_VSCROLL,    // vertical scroll bar
      CW_USEDEFAULT,          // default horizontal position
      CW_USEDEFAULT,          // default vertical position
      CW_USEDEFAULT,          // default width
      CW_USEDEFAULT,          // default height
      null,            // no parent or owner window
      null,           // class menu used
      hinst,              // instance handle
      null)                   // no window creation data

  /* if (hwndMain == null)
      return -1 */

  // Show the window using the flag specified by the program
  // that started the application, and send the application
  // a WM_PAINT message.

  ShowWindow(hwndMain, SW_SHOWDEFAULT)
  UpdateWindow(hwndMain)
  return 1
}
