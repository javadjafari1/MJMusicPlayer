package ir.thatsmejavad.mjmusic.screens.music

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.thatsmejavad.mjmusic.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicScreen(
    navController: NavController
) {
    val tabLabels = listOf(
        "Albums",
        "Artists",
        "Songs",
        "Playlists",
        "Genres",
        "Folders",
    )

    val pagerState = rememberPagerState { tabLabels.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ScrollableTabRow(
            modifier = Modifier.padding(8.dp),
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                )
            },
        ) {
            tabLabels.forEachIndexed { index, title ->
                val selectedTab = pagerState.currentPage == index
                Tab(
                    modifier = Modifier.clip(MaterialTheme.shapes.small),
                    selected = selectedTab,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
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
                if (page == 0) {
                    val songList = mutableListOf<SongModel>()
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", true))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))
                    songList.add(SongModel("The girl", "Homa Shafiei", false))

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Name",
                                style = typography.bodyMedium
                            )
                            Icon(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 4.dp)
                                    .size(20.dp)
                                    .clickable { },
                                painter = painterResource(R.drawable.ic_arrow_top),
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = "image-cover"
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 4.dp)
                                    .size(24.dp)
                                    .clickable { },
                                painter = painterResource(R.drawable.ic_shuffle),
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = "image-cover"
                            )
                            Text(
                                text = " Songs",
                                style = typography.bodyMedium
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                        ) {
                            songList.forEach { song ->
                                SongItem(song = song) {

                                }
                            }
                        }
                    }
                } else {
                    Text(text = "${tabLabels[page]} ${navController.currentBackStackEntry?.destination?.route} Screen")
                }
            }
        }
    }
}

@Composable
fun SongItem(
    song: SongModel,
    onSongClick: () -> Unit
) {
    val backgroundColor = if (song.isSelected) {
        MaterialTheme.colorScheme.inversePrimary
    } else {
        MaterialTheme.colorScheme.surface
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 32.dp)
                .clip(shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                )
                .weight(1f)
                .clickable(onClick = onSongClick),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .size(64.dp),
                painter = painterResource(R.drawable.ic_bottom_bar_music),
                contentScale = ContentScale.Crop,
                contentDescription = "image-cover"
            )

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = song.title,
                    style = typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = song.artist,
                    style = typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        Icon(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp)
                .size(24.dp)
                .clickable { },
            painter = painterResource(R.drawable.ic_favorit),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = "image-cover"
        )
        Icon(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .size(24.dp)
                .clickable { },
            painter = painterResource(R.drawable.ic_more),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = "image-cover"
        )
    }
}

data class SongModel(
    val title: String,
    val artist: String,
    val isSelected: Boolean
)