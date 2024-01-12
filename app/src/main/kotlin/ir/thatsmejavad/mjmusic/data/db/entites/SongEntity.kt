package ir.thatsmejavad.mjmusic.data.db.entites

import android.net.Uri
import android.provider.MediaStore
import androidx.media3.common.MediaItem
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    @ColumnInfo("song_id")
    val id: Long,
    val name: String?,
    val title: String?,
    @ColumnInfo("album_id")
    val albumId: Long,
    @ColumnInfo(name = "mime_type")
    val mimeType: String?,
    @ColumnInfo(name = "added_date")
    val addedDate: Long,
    @ColumnInfo(name = "modified_date")
    val modifiedDate: Long,
    val duration: Long,
    val year: Int,
    val size: Long,
    val track: Int,
    @ColumnInfo(name = "bit_rate")
    val bitRate: String
) {
    fun toMediaItem(): MediaItem {
        return MediaItem.Builder()
            .setMediaId(id.toString())
            .setUri(
                Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    id.toString()
                )
            )
            .build()
    }
}
