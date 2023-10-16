package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = ["song_id", "artist_id"]
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
