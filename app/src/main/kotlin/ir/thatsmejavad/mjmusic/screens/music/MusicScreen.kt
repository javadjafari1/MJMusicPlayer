package ir.thatsmejavad.mjmusic.screens.music

import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
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
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.navigation.NavController
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioEvent
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioHandler
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.provider.AudioProviderImpl
import ir.thatsmejavad.mjmusic.screens.music.MusicAction.OnAudioSelect
import ir.thatsmejavad.mjmusic.ui.common.MusicCategory
import ir.thatsmejavad.mjmusic.ui.common.ScrollableTabBar
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
    val audioProviderImpl = AudioProviderImpl()
    val context = LocalContext.current

    //TODO we should be change later, this is just for test UI
    val songs by remember {
        mutableStateOf(audioProviderImpl.getSongs(context))
    }

    MusicScreen(
        navController = navController,
        songs = songs,
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
    songs: List<AudioColumns>,
    selectedSongId: Long,
    onAction: (MusicAction) -> Unit
) {
    val tabLabels = MusicCategory.entries.map { stringResource(it.value) }
    val pagerState = rememberPagerState { tabLabels.size }
    val coroutineScope = rememberCoroutineScope()
    var isSortedAtoZ by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        ScrollableTabBar(
            labels = tabLabels,
            selectedTabIndex = pagerState.currentPage,
            onTabClicked = { index ->
                coroutineScope.launch { pagerState.animateScrollToPage(index) }
            }
        )

        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = pagerState
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (tabLabels[page] == stringResource(R.string.label_songs)) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable { isSortedAtoZ = !isSortedAtoZ },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = "Name",
                                    style = typography.bodyMedium
                                )

                                Icon(
                                    modifier = Modifier.padding(8.dp).size(16.dp),
                                    painter = if (isSortedAtoZ) {
                                        painterResource(R.drawable.ic_arrow_top)
                                    } else {
                                        painterResource(R.drawable.ic_arrow_bottom)
                                    },
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    contentDescription = "sort"
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .clip(shape = MaterialTheme.shapes.small)
                                    .clickable {  },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .size(16.dp)
                                        .align(Alignment.CenterVertically),
                                    painter = painterResource(R.drawable.ic_shuffle),
                                    tint = MaterialTheme.colorScheme.onSurface,
                                    contentDescription = "shuffle"
                                )

                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = " ${songs.count()} Songs",
                                    style = typography.bodyMedium
                                )
                            }
                        }

                        LazyColumn {
                            items(
                                items = songs,
                                itemContent = { song ->
                                    SongItem(
                                        song = song,
                                        selectedSongId = selectedSongId,
                                    ) {
                                        onAction(OnAudioSelect(song))
                                    }
                                }
                            )
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clip(shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
            )
            .clickable(onClick = {
                onSongClick()
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //TODO we need change this icon
        IconButton(
            modifier = Modifier.padding(8.dp),
            onClick = { }
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "play-arrow"
            )
        }

        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f)
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

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "favorite"
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
