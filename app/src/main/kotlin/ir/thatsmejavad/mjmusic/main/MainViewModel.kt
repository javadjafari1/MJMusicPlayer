package ir.thatsmejavad.mjmusic.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.mjmusic.data.db.daos.AlbumDao
import ir.thatsmejavad.mjmusic.data.db.daos.ArtistDao
import ir.thatsmejavad.mjmusic.data.db.daos.SongArtistCrossRefDao
import ir.thatsmejavad.mjmusic.data.db.daos.SongDao
import ir.thatsmejavad.mjmusic.data.db.entites.AlbumEntity
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.PersonRole
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity
import ir.thatsmejavad.mjmusic.data.db.relations.AlbumWithSongs
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val songDao: SongDao,
    private val albumDao: AlbumDao,
    private val artistDao: ArtistDao,
    private val songArtistCrossRefDao: SongArtistCrossRefDao
) : ViewModel() {

    val songsFlow = MutableSharedFlow<List<AlbumWithSongs>>()

    init {
        viewModelScope.launch {
            val newOnes = List(200) {
                SongEntity(
                    id = 10_000L + it,
                    name = "Wow $it",
                    albumId = when {
                        it % 5 == 0 -> 1000
                        it % 3 == 0 -> 2000
                        else -> 3000
                    },
                    mimeType = "mp3",
                    addedDate = System.currentTimeMillis(),
                    modifiedDate = System.currentTimeMillis(),
                    duration = 2000,
                    size = 123000,
                    track = 1,
                    year = 1995 + it,
                    bitRate = "320"
                )
            }
            val albums = listOf(
                AlbumEntity(
                    id = 1000,
                    name = "Album 1",
                    year = 2000
                ),
                AlbumEntity(
                    id = 2000,
                    name = "Album 3",
                    year = 2000
                ),
                AlbumEntity(
                    id = 3000,
                    name = "Album 3",
                    year = 2000
                ),
            )
            val artists = List(20) {
                ArtistEntity(
                    id = it.toLong(),
                    name = "Artist $it"
                )
            }
            val albumArtist = List(200) {
                SongArtistCrossRefEntity(
                    songId = 10_000L + it,
                    artistId = when {
                        it % 3 == 0 -> 5
                        it % 4 == 0 -> 6
                        it % 5 == 0 -> 7
                        it % 11 == 0 -> 8
                        else -> 9
                    },
                    role = if (it % 2 == 0) PersonRole.Artist else PersonRole.Composer
                )
            }

            albumDao.insertAlbums(albums)
            songDao.insertSongs(newOnes)
            artistDao.insertArtists(artists)
            songArtistCrossRefDao.insert(albumArtist)

            songsFlow.emit(albumDao.getAlbumsWithSongs())
        }
    }
}
