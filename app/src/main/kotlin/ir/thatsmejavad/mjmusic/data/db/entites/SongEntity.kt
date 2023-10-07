package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val albumId: Long,
    val data: String,
    @ColumnInfo(name = "mime_type")
    val mimeType: String,
    @ColumnInfo(name = "date_added")
    val dateAdded: Long,
    @ColumnInfo(name = "date_modified")
    val dateModified: Long,
    val duration: Int,
    val year: Int,
    val size: Long,
    val track: Int,
    @ColumnInfo(name = "bit_rate")
    val bitRate: String
)