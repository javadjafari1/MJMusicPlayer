package ir.thatsmejavad.mjmusic.main

import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.core.audioPlayer.PlaybackService
import ir.thatsmejavad.mjmusic.mainNavGraph
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBarItem
import ir.thatsmejavad.mjmusic.ui.bottombar.BottomBarItem.Companion.getBottomBarItemForDestination
import ir.thatsmejavad.mjmusic.ui.bottombar.MJMusicBottomBar
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme

class MainActivity : ComponentActivity() {

    private lateinit var controllerFuture: ListenableFuture<MediaController>

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberNavController(bottomSheetNavigator)
            MJMusicTheme {
                ChangeSystemBarsColors()

                Scaffold(
                    bottomBar = {
                        val navStackBackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navStackBackEntry?.destination
                        MJMusicBottomBar(
                            modifier = Modifier.navigationBarsPadding(),
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

    override fun onStart() {
        super.onStart()
        setupMediaController()
     }

    override fun onStop() {
        super.onStop()
        MediaController.releaseFuture(controllerFuture)
    }

    @Composable
    private fun ChangeSystemBarsColors() {
        val systemInDarkTheme = isSystemInDarkTheme()
        SideEffect {
            val systemBarStyle = if (systemInDarkTheme) {
                SystemBarStyle.dark(
                    scrim = Color.Transparent.toArgb()
                )
            } else {
                SystemBarStyle.light(
                    scrim = Color.Transparent.toArgb(),
                    darkScrim = Color.Black.copy(SCRIM_ALPHA_ON_BELOW_29).toArgb()
                )
            }
            enableEdgeToEdge(
                statusBarStyle = systemBarStyle,
                navigationBarStyle = systemBarStyle
            )
        }
    }

    private fun setupMediaController() {
        val sessionToken = SessionToken(
            this,
            ComponentName(this, PlaybackService::class.java)
        )
        controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
        controllerFuture.addListener({}, MoreExecutors.directExecutor())
    }

    companion object {
        private const val SCRIM_ALPHA_ON_BELOW_29 = 0.3f
    }
}
