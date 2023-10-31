package ir.thatsmejavad.mjmusic.core.contentProvider.projection

import android.provider.MediaStore

val AUDIO_PROJECTION = arrayOf(
    MediaStore.Audio.AudioColumns._ID,
    MediaStore.Audio.AudioColumns.DISPLAY_NAME,
    MediaStore.Audio.AudioColumns.TITLE,
    MediaStore.Audio.AudioColumns.DURATION,
    MediaStore.Audio.AudioColumns.ARTIST_ID,
    MediaStore.Audio.AudioColumns.ARTIST,
    MediaStore.Audio.AudioColumns.ALBUM_ID,
    MediaStore.Audio.AudioColumns.ALBUM,
    MediaStore.Audio.AudioColumns.ALBUM_ARTIST,
    MediaStore.Audio.AudioColumns.TRACK,
    MediaStore.Audio.AudioColumns.YEAR,
    MediaStore.Audio.AudioColumns.COMPOSER,
    MediaStore.Audio.AudioColumns.MIME_TYPE,
    MediaStore.Audio.AudioColumns.SIZE,
    MediaStore.Audio.AudioColumns.IS_ALARM,
    MediaStore.Audio.AudioColumns.IS_MUSIC,
    MediaStore.Audio.AudioColumns.IS_NOTIFICATION,
    MediaStore.Audio.AudioColumns.IS_PODCAST,
    MediaStore.Audio.AudioColumns.IS_RINGTONE,
    MediaStore.Audio.AudioColumns.DATE_ADDED,
    MediaStore.Audio.AudioColumns.DATE_MODIFIED,
    MediaStore.Audio.AudioColumns.IS_DRM
)

object AudioProjectionIndexes {
    val id = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns._ID)
    val displayName = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.DISPLAY_NAME)
    val title = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.TITLE)
    val duration = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.DURATION)
    val artistId = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.ARTIST_ID)
    val artist = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.ARTIST)
    val albumId = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.ALBUM_ID)
    val album = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.ALBUM)
    val albumArtist = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.ALBUM_ARTIST)
    val track = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.TRACK)
    val year = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.YEAR)
    val composer = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.COMPOSER)
    val mimeType = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.MIME_TYPE)
    val size = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.SIZE)
    val isAlarm = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_ALARM)
    val isMusic = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_MUSIC)
    val isNotification = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_NOTIFICATION)
    val isPodcast = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_PODCAST)
    val isRingtone = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_RINGTONE)
    val dateAdded = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.DATE_ADDED)
    val dateModified = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.DATE_MODIFIED)
    val isDrm = AUDIO_PROJECTION.indexOf(MediaStore.Audio.AudioColumns.IS_DRM)
}
