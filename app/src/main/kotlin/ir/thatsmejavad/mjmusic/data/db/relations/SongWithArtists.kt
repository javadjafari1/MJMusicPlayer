package ir.thatsmejavad.mjmusic.data.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

data class SongWithArtists(
    @Embedded
    val song: SongEntity,
    @Relation(
        parentColumn = "song_id",
        entityColumn = "artist_id",
        associateBy = Junction(SongArtistCrossRefEntity::class)
    )
    val artists: List<ArtistEntity>
)
