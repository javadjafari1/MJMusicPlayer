package ir.thatsmejavad.mjmusic.ui.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavDestination
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.ApplicationScreens

sealed class BottomBarItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    @DrawableRes val focusedIcon: Int
) {
    data object Home : BottomBarItem(
        route = ApplicationScreens.Home.route,
        title = R.string.label_home,
        icon = R.drawable.ic_bottom_bar_home,
        focusedIcon = R.drawable.ic_bottom_bar_home_focused
    )

    data object Search : BottomBarItem(
        route = ApplicationScreens.Search.route,
        title = R.string.label_search,
        icon = R.drawable.ic_bottom_bar_search,
        focusedIcon = R.drawable.ic_bottom_bar_search_focused
    )

    data object Music : BottomBarItem(
        route = ApplicationScreens.Music.route,
        title = R.string.label_music,
        icon = R.drawable.ic_bottom_bar_music,
        focusedIcon = R.drawable.ic_bottom_bar_music_focused
    )

    data object Setting : BottomBarItem(
        route = ApplicationScreens.Setting.route,
        title = R.string.label_setting,
        icon = R.drawable.ic_bottom_bar_setting,
        focusedIcon = R.drawable.ic_bottom_bar_setting_focused
    )

    companion object {
        val items = listOf(
            Home,
            Search,
            Music,
            Setting
        )

        fun getBottomBarItemForDestination(destination: NavDestination?): BottomBarItem? {
            return when (destination?.route) {
                ApplicationScreens.Home.route -> Home
                ApplicationScreens.Search.route -> Search
                ApplicationScreens.Music.route -> Music
                ApplicationScreens.Setting.route -> Setting
                else -> null
            }
        }
    }
}
