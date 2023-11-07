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
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.mainNavGraph
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBarItem
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBarItem.Companion.getBottomBarItemForDestination
import ir.thatsmejavad.mjmusic.ui.bottombar.MJMusicBottomBar
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberNavController(bottomSheetNavigator)
            MJMusicTheme {
                Scaffold(
                    bottomBar = {
                        val navStackBackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navStackBackEntry?.destination
                        MJMusicBottomBar(
                            items = BottomBarItem.items,
                            currentBottomBarItem = getBottomBarItemForDestination(currentDestination),
                            onItemClick = { item ->
                                navController.navigate(item.route) {
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
                    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
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
}
