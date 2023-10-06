package ir.thatsmejavad.mjmusic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.core.animatedComposable
import ir.thatsmejavad.mjmusic.screens.home.HomeScreen

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    animatedComposable(
        route = ApplicationScreens.Home.route
    ) {
        HomeScreen(navController = navController)
    }
}