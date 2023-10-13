package ir.thatsmejavad.mjmusic.core.contentProvider.model

data class AudioColumns(
    val id: Long,
    val data: String?,// Path to the audio file.
    val displayName: String?,
    val title: String?,
    val duration: Long,
    val artistId: Long,
    val artist: String?,
    val albumId: Long,
    val album: String?,
    val albumArtist: String?,
    val track: Int,// Track number of the audio within the album.
    val year: Int,
    val composer: String?,
    val mimeType: String?,
    val size: Long,
    val isAlarm: Boolean,
    val isMusic: Boolean,
    val isNotification: Boolean,
    val isPodcast: Boolean,
    val isRingtone: Boolean,
    val dateAdded: String?,
    val dateModified: String?,
    val isDrm: String?,
)
