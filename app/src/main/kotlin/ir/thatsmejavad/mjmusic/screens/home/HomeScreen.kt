package ir.thatsmejavad.mjmusic.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Text(text = "Implementation ${navController.currentBackStackEntry?.destination?.route} Screen Later")
}
