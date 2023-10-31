package ir.thatsmejavad.mjmusic.core.contentProvider.provider

import android.content.Context
import android.provider.MediaStore
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AlbumColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.ArtistColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns
import kotlinx.coroutines.flow.Flow

interface AudioProvider {
    fun getSongs(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Media.TITLE,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): List<AudioColumns>

    fun observeSongs(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Media.TITLE,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): Flow<List<AudioColumns>>

    fun getArtist(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Artists.ARTIST,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): List<ArtistColumns>

    fun observeArtist(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Artists.ARTIST,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): Flow<List<ArtistColumns>>

    fun getAlbums(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Albums.ALBUM,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): List<AlbumColumns>

    fun observeAlbums(
        context: Context,
        selection: String = "",
        selectionArguments: Array<String>? = null,
        order: String = MediaStore.Audio.Albums.ALBUM,
        ascending: Boolean = true,
        offset: Int = 0,
        limit: Int = Int.MAX_VALUE
    ): Flow<List<AlbumColumns>>
}
