package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
)
