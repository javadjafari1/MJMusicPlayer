package ir.thatsmejavad.mjmusic.core.contentProvider.extension

import android.database.Cursor
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AlbumColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.ArtistColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.AlbumProjectionIndexes
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.ArtistProjectionIndexes
import ir.thatsmejavad.mjmusic.core.contentProvider.projection.AudioProjectionIndexes

fun Cursor.toAudioColumnsData() = AudioColumns(
    id = getLong(AudioProjectionIndexes.id),
    data = getString(AudioProjectionIndexes.data),
    displayName = getString(AudioProjectionIndexes.displayName),
    title = getString(AudioProjectionIndexes.title),
    duration = getLong(AudioProjectionIndexes.duration),
    artistId = getLong(AudioProjectionIndexes.artistId),
    artist = getString(AudioProjectionIndexes.artist),
    albumId = getLong(AudioProjectionIndexes.albumId),
    album = getString(AudioProjectionIndexes.album),
    albumArtist = getString(AudioProjectionIndexes.albumArtist),
    track = getInt(AudioProjectionIndexes.track),
    year = getInt(AudioProjectionIndexes.year),
    composer = getString(AudioProjectionIndexes.composer),
    mimeType = getString(AudioProjectionIndexes.mimeType),
    size = getLong(AudioProjectionIndexes.size),
    isAlarm = getInt(AudioProjectionIndexes.isAlarm) != 0,
    isMusic = getInt(AudioProjectionIndexes.isMusic) != 0,
    isNotification = getInt(AudioProjectionIndexes.isNotification) != 0,
    isPodcast = getInt(AudioProjectionIndexes.isPodcast) != 0,
    isRingtone = getInt(AudioProjectionIndexes.isRingtone) != 0,
    dateAdded = getString(AudioProjectionIndexes.dateAdded),
    dateModified = getString(AudioProjectionIndexes.dateModified),
    isDrm = getString(AudioProjectionIndexes.isDrm)
)

fun Cursor.toArtistColumnsData() = ArtistColumns(
    artist = getString(ArtistProjectionIndexes.artis),
    numberOfAlbums = getInt(ArtistProjectionIndexes.numberOfAlbums),
    numberOfTracks = getInt(ArtistProjectionIndexes.numberOfTracks)
)

fun Cursor.toAlbumColumnsData() = AlbumColumns(
    album = getString(AlbumProjectionIndexes.album),
    artist = getString(AlbumProjectionIndexes.artist),
    numberOfSongs = getInt(AlbumProjectionIndexes.numberOfSongs),
    firstYear = getInt(AlbumProjectionIndexes.firstYear),
    lastYear = getInt(AlbumProjectionIndexes.lastYear)
)
