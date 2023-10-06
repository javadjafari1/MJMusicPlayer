package ir.thatsmejavad.mjmusic.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.mjmusic.core.ApplicationScreens
import ir.thatsmejavad.mjmusic.mainNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Box {
                NavHost(
                    navController = navController,
                    startDestination = ApplicationScreens.Home.route
                ) {
                    mainNavGraph(navController)
                }
            }
        }
    }
}
