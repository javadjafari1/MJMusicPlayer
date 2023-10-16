package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    @ColumnInfo("song_id")
    val id: Long,
    val name: String,
    @ColumnInfo("album_id")
    val albumId: Long,
    @ColumnInfo(name = "mime_type")
    val mimeType: String,
    @ColumnInfo(name = "added_date")
    val addedDate: Long,
    @ColumnInfo(name = "modified_date")
    val modifiedDate: Long,
    val duration: Int,
    val year: Int,
    val size: Long,
    val track: Int,
    @ColumnInfo(name = "bit_rate")
    val bitRate: String
)