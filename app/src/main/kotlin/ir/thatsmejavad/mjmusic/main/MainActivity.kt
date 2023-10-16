package ir.thatsmejavad.mjmusic.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ir.thatsmejavad.mjmusic.ui.theme.MJMusicTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainViewModel = getViewModel()
        setContent {
            val items by mainViewModel.songsFlow.collectAsState(listOf())
            val navController = rememberNavController()
            MJMusicTheme {
                LazyColumn {
                    items(items) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .heightIn(min = 40.dp)
                                .border(
                                    width = 4.dp,
                                    color = MaterialTheme.colorScheme.surface,
                                    shape = MaterialTheme.shapes.large
                                )
                        ) {
                            Text(text = it.albumEntity.name)
                            Spacer(modifier = Modifier.size(24.dp))
                            Column {
                                it.songsWithArtists.forEach {
                                    Text(text = it.song.name)
                                    it.artists.forEach {
                                        Text(text = it.name)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
