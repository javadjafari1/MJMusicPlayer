package ir.thatsmejavad.mjmusic.screens.music

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicScreen(
    navController: NavController
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabLabels = listOf(
        "Albums",
        "Artists",
        "Songs",
        "Playlists",
        "Genres",
        "Folders",
    )

    val pagerState = rememberPagerState { tabLabels.size }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            tabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ScrollableTabRow(
            modifier = Modifier.padding(8.dp),
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                if (tabIndex < tabPositions.size) {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    )
                }
            },
        ) {
            tabLabels.forEachIndexed { index, title ->
                val selectedTab = tabIndex == index
                Tab(
                    modifier = Modifier.clip(MaterialTheme.shapes.small),
                    selected = selectedTab,
                    onClick = { tabIndex = index },
                    text = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = if (selectedTab) {
                                MaterialTheme.colorScheme.surfaceTint
                            } else {
                                MaterialTheme.colorScheme.onBackground
                            },
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = true,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${tabLabels[page]} ${navController.currentBackStackEntry?.destination?.route} Screen")
            }
        }
    }
}
