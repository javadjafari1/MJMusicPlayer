package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

@Entity(
    primaryKeys = ["song_id", "artist_id", "role"],
    indices = [Index("song_id"), Index("artist_id")],
    tableName = "song_artist_cross_ref",
)
data class SongArtistCrossRefEntity(
    @ColumnInfo("song_id")
    val songId: Long,
    @ColumnInfo("artist_id")
    val artistId: Long,
    val role: PersonRole
)

enum class PersonRole {
    Artist,
    Composer
}
