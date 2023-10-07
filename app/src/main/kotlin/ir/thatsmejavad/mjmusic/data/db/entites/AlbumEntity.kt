package ir.thatsmejavad.mjmusic.data.db.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val year: Int
)
