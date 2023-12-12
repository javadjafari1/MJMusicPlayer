package ir.thatsmejavad.mjmusic.screens.music

import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.navigation.NavController
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioEvent
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioHandler
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.provider.AudioProviderImpl
import ir.thatsmejavad.mjmusic.screens.music.MusicAction.OnAudioSelect
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun MusicScreen(
    navController: NavController,
) {
    MusicScreen(
        navController = navController,
        audioHandler = koinInject()
    )
}

@Composable
fun MusicScreen(
    navController: NavController,
    audioHandler: AudioHandler,
) {
    var selectedSongId by remember { mutableLongStateOf(0) }

    MusicScreen(
        navController = navController,
        selectedSongId = selectedSongId
    ) { action ->
        when (action) {
            is OnAudioSelect -> {
                val audioUri = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    action.song.id.toString()
                )
                selectedSongId = action.song.id
                audioHandler.addMediaItem(MediaItem.fromUri(audioUri))
                audioHandler.setEvent(AudioEvent.PlayPause)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicScreen(
    navController: NavController,
    selectedSongId: Long,
    onAction: (MusicAction) -> Unit
) {
    val tabLabels = listOf(
        stringResource(R.string.label_albums),
        stringResource(R.string.label_artists),
        stringResource(R.string.label_songs),
        stringResource(R.string.label_playlists),
        stringResource(R.string.label_genres),
        stringResource(R.string.label_folders),
    )

    val pagerState = rememberPagerState { tabLabels.size }
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val audioProviderImpl = AudioProviderImpl()

    val songs by remember {
        mutableStateOf(audioProviderImpl.getSongs(context))
    }

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
                if (page == 2) {
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
                                text = " ${songs.count()} Songs",
                                style = typography.bodyMedium
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                        ) {
                            songs.forEach { song ->
                                SongItem(
                                    song = song,
                                    selectedSongId = selectedSongId,
                                ) {
                                    onAction(OnAudioSelect(song))
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
    song: AudioColumns,
    selectedSongId: Long,
    onSongClick: () -> Unit
) {
    val backgroundColor = if (song.id == selectedSongId) {
        MaterialTheme.colorScheme.inversePrimary
    } else {
        MaterialTheme.colorScheme.surface
    }
    Row(
        modifier = Modifier.padding(vertical = 2.dp),
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
                .clickable(onClick = {
                    onSongClick()
                }),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = { }
            ) {
                Icon(
                    modifier = Modifier.size(42.dp),
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = "PlayArrow"
                )
            }

            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = song.title ?: "",
                    style = typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = song.artist ?: "",
                    style = typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite"
            )
        }
        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                contentDescription = "more"
            )
        }
    }
}
