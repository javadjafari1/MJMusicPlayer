package ir.thatsmejavad.mjmusic.data.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import ir.thatsmejavad.mjmusic.data.db.entites.AlbumEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

data class AlbumWithSongs(
    @Embedded
    val albumEntity: AlbumEntity,
    @Relation(
        entity = SongEntity::class,
        parentColumn = "id",
        entityColumn = "album_id"
    )
    val songsWithArtists: List<SongWithArtists>
)
