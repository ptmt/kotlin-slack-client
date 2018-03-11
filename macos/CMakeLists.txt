cmake_minimum_required(VERSION 3.8)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_LIST_DIR}/KotlinCMakeModule)

project(macos Kotlin)

konanc_executable(
    NAME KotlinSlack
    SOURCES src/main/kotlin/Window.kt)

