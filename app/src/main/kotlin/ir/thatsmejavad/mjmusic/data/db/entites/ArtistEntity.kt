package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey
    @ColumnInfo("artist_id")
    val id: Long,
    val name: String,
)
