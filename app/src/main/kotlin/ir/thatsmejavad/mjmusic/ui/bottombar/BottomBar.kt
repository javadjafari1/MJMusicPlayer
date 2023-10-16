package ir.thatsmejavad.mjmusic.ui.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavController
) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Search,
        BottomBarItem.Music,
        BottomBarItem.Setting
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            AddBottomBarItem(
                screen = screen,
                navController = navController,
                selected = selected
            )
        }
    }
}

@Composable
private fun RowScope.AddBottomBarItem(
    screen: BottomBarItem,
    navController: NavController,
    selected: Boolean
) {
    val background =
        if (selected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 18.dp)
            .weight(if (selected) 1f else WEIGHT_7)
            .clip(MaterialTheme.shapes.small)
            .background(background)
            .clickable {
                if (!selected) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            painter = painterResource(if (selected) screen.iconFocused else screen.icon),
            contentDescription = null
        )
        if (selected) {
            Text(
                text = screen.title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

private const val WEIGHT_7 = .7f
