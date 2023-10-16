package ir.thatsmejavad.mjmusic.ui.bottombar

import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.ApplicationScreens

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val icon: Int,
    val iconFocused: Int
) {
    data object Home : BottomBarItem(
        route = ApplicationScreens.Home.route,
        title = "Home",
        icon = R.drawable.ic_bottom_bar_home,
        iconFocused = R.drawable.ic_bottom_bar_home_focused
    )

    data object Search : BottomBarItem(
        route = ApplicationScreens.Search.route,
        title = "Search",
        icon = R.drawable.ic_bottom_bar_search,
        iconFocused = R.drawable.ic_bottom_bar_search_focused
    )

    data object Music : BottomBarItem(
        route = ApplicationScreens.Music.route,
        title = "Music",
        icon = R.drawable.ic_bottom_bar_music,
        iconFocused = R.drawable.ic_bottom_bar_music_focused
    )

    data object Setting : BottomBarItem(
        route = ApplicationScreens.Setting.route,
        title = "Setting",
        icon = R.drawable.ic_bottom_bar_setting,
        iconFocused = R.drawable.ic_bottom_bar_setting_focused
    )
}
