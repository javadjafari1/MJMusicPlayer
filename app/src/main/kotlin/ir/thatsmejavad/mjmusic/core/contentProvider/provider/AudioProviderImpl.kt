package ir.thatsmejavad.mjmusic.core.contentProvider.provider

import android.content.Context
import android.provider.MediaStore
import ir.thatsmejavad.mjmusic.core.contentProvider.extension.observeChanges
import ir.thatsmejavad.mjmusic.core.contentProvider.extension.query
import ir.thatsmejavad.mjmusic.core.contentProvider.extension.toAlbumColumnsData
import ir.thatsmejavad.mjmusic.core.contentProvider.extension.toArtistColumnsData
import ir.thatsmejavad.mjmusic.core.contentProvider.extension.toAudioColumnsData
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AlbumColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.ArtistColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.ALBUM_PROJECTION
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.ARTIST_PROJECTION
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.AUDIO_PROJECTION
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AudioProviderImpl : AudioProvider {
    override fun getSongs(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): List<AudioColumns> {
        val songs = mutableListOf<AudioColumns>()
        val cursor = context.contentResolver.query(
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection = AUDIO_PROJECTION,
            selection = selection,
            selectionArguments = selectionArguments,
            order = order,
            ascending = ascending,
            offset = offset,
            limit = limit
        )
        while (cursor?.moveToNext() == true) {
            songs.add(cursor.toAudioColumnsData())
        }
        return songs
    }

    override fun observeSongs(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): Flow<List<AudioColumns>> = flow {
        context.contentResolver.observeChanges(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            .collect {
                emit(
                    getSongs(
                        context = context,
                        selection = selection,
                        selectionArguments = selectionArguments,
                        order = order,
                        ascending = ascending,
                        offset = offset,
                        limit = limit
                    )
                )
            }
    }

    override fun getArtist(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): List<ArtistColumns> {
        val artist = mutableListOf<ArtistColumns>()
        val cursor = context.contentResolver.query(
            uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            projection = ARTIST_PROJECTION,
            selection = selection,
            selectionArguments = selectionArguments,
            order = order,
            ascending = ascending,
            offset = offset,
            limit = limit
        )
        while (cursor?.moveToNext() == true) {
            artist.add(cursor.toArtistColumnsData())
        }
        return artist
    }

    override fun observeArtist(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): Flow<List<ArtistColumns>> = flow {
        context.contentResolver.observeChanges(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI)
            .collect {
                val artist = mutableListOf<ArtistColumns>()
                val cursor = context.contentResolver.query(
                    uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                    projection = ARTIST_PROJECTION,
                    selection = selection,
                    selectionArguments = selectionArguments,
                    order = order,
                    ascending = ascending,
                    offset = offset,
                    limit = limit
                )
                while (cursor?.moveToNext() == true) {
                    artist.add(cursor.toArtistColumnsData())
                }
                emit(artist)
            }
    }

    override fun getAlbums(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): List<AlbumColumns> {
        val albums = mutableListOf<AlbumColumns>()
        val cursor = context.contentResolver.query(
            uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            projection = ALBUM_PROJECTION,
            selection = selection,
            selectionArguments = selectionArguments,
            order = order,
            ascending = ascending,
            offset = offset,
            limit = limit
        )
        while (cursor?.moveToNext() == true) {
            albums.add(cursor.toAlbumColumnsData())
        }
        return albums
    }

    override fun observeAlbums(
        context: Context,
        selection: String,
        selectionArguments: Array<String>?,
        order: String,
        ascending: Boolean,
        offset: Int,
        limit: Int
    ): Flow<List<AlbumColumns>> = flow {
        context.contentResolver.observeChanges(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI)
            .collect {
                val albums = mutableListOf<AlbumColumns>()
                val cursor = context.contentResolver.query(
                    uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    projection = ALBUM_PROJECTION,
                    selection = selection,
                    selectionArguments = selectionArguments,
                    order = order,
                    ascending = ascending,
                    offset = offset,
                    limit = limit
                )
                while (cursor?.moveToNext() == true) {
                    albums.add(cursor.toAlbumColumnsData())
                }
                emit(albums)
            }
    }
}
