package ir.thatsmejavad.mjmusic.core.contentProvider.model

import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

data class AudioColumns(
    val id: Long,
    val displayName: String?,
    val title: String?,
    val duration: Long,
    val artistId: Long,
    val artist: String?,
    val albumId: Long,
    val album: String?,
    val albumArtist: String?,
    val track: Int,
    val year: Int,
    val composer: String?,
    val mimeType: String?,
    val size: Long,
    val isAlarm: Boolean,
    val isMusic: Boolean,
    val isNotification: Boolean,
    val isPodcast: Boolean,
    val isRingtone: Boolean,
    val dateAdded: Long?,
    val dateModified: Long?,
    val isDrm: String?,
) {
    fun toSongEntity() = SongEntity(
        id = id,
        name = displayName,
        title = title,
        albumId = albumId,
        mimeType = mimeType,
        addedDate = dateAdded ?: 0,
        modifiedDate = dateModified ?: 0,
        duration = duration,
        year = year,
        size = size,
        track = track,
        bitRate = ""
    )
}
