package ir.thatsmejavad.mjmusic.core.contentProvider.projection

import android.provider.MediaStore

val ARTIST_PROJECTION = arrayOf(
    MediaStore.Audio.ArtistColumns.ARTIST,
    MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS,
    MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS
)

object ArtistProjectionIndexes {
    val artis = ARTIST_PROJECTION.indexOf(MediaStore.Audio.ArtistColumns.ARTIST)
    val numberOfAlbums = ARTIST_PROJECTION.indexOf(MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS)
    val numberOfTracks = ARTIST_PROJECTION.indexOf(MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS)
}
