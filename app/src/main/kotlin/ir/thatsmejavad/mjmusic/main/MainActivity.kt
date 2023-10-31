package ir.thatsmejavad.mjmusic.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.mainNavGraph
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBar
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBarItem
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MJMusicTheme {
                Scaffold(
                    bottomBar = {
                        val navStackBackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navStackBackEntry?.destination?.route
                        BottomBar(
                            screens = BottomBarItem.screens,
                            currentDestination = currentDestination,
                            onItemClick = { route ->
                                navController.navigate(route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                ) { padding ->
                    NavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = ApplicationScreens.Home.route
                    ) {
                        mainNavGraph(navController)
                    }
                }
            }
        }
    }
}
