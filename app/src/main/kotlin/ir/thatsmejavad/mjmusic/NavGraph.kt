package ir.thatsmejavad.mjmusic

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.core.animatedComposable
import ir.thatsmejavad.mjmusic.screens.home.HomeScreen
import ir.thatsmejavad.mjmusic.screens.home.PermissionsScreen
import ir.thatsmejavad.mjmusic.screens.music.MusicScreen
import ir.thatsmejavad.mjmusic.screens.search.SearchScreen
import ir.thatsmejavad.mjmusic.screens.setting.SettingScreen

fun NavGraphBuilder.mainNavGraph(
    navController: NavController
) {
    animatedComposable(
        route = ApplicationScreens.Permissions.route
    ) {
        PermissionsScreen(navController = navController)
    }
    animatedComposable(ApplicationScreens.Home.route) {
        HomeScreen(navController)
    }
    animatedComposable(ApplicationScreens.Search.route) {
        SearchScreen(navController)
    }
    animatedComposable(ApplicationScreens.Music.route) {
        MusicScreen(navController)
    }
    animatedComposable(ApplicationScreens.Setting.route) {
        SettingScreen(navController)
    }
}
