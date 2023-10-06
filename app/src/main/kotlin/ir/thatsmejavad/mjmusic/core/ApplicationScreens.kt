package ir.thatsmejavad.mjmusic.core

internal sealed class ApplicationScreens(val route: String) {
    data object Home : ApplicationScreens("Home")
}