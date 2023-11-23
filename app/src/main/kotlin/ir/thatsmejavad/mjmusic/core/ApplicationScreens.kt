package ir.thatsmejavad.mjmusic.core

internal sealed class ApplicationScreens(val route: String) {
    data object Permissions : ApplicationScreens("Permissions")
    data object Home : ApplicationScreens("home")
    data object Search : ApplicationScreens("search")
    data object Music : ApplicationScreens("music")
    data object Setting : ApplicationScreens("setting")
}
