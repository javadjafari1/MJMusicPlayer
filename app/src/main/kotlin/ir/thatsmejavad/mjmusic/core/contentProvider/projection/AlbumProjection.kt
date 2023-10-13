package ir.thatsmejavad.mjmusic.core.contentProvider.projection

import android.provider.MediaStore

val ALBUM_PROJECTION = arrayOf(
    MediaStore.Audio.AlbumColumns.ALBUM,
    MediaStore.Audio.AlbumColumns.ARTIST,
    MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
    MediaStore.Audio.AlbumColumns.FIRST_YEAR,
    MediaStore.Audio.AlbumColumns.LAST_YEAR
)

object AlbumProjectionIndexes {
    val album = ALBUM_PROJECTION.indexOf(MediaStore.Audio.AlbumColumns.ALBUM)
    val artist = ALBUM_PROJECTION.indexOf(MediaStore.Audio.AlbumColumns.ARTIST)
    val numberOfSongs = ALBUM_PROJECTION.indexOf(MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS)
    val firstYear = ALBUM_PROJECTION.indexOf(MediaStore.Audio.AlbumColumns.FIRST_YEAR)
    val lastYear = ALBUM_PROJECTION.indexOf(MediaStore.Audio.AlbumColumns.LAST_YEAR)
}
