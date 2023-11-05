package ir.thatsmejavad.mjmusic.data.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

data class ArtistsWithSongs(
    @Embedded
    val artist: ArtistEntity,
    @Relation(
        parentColumn = "artist_id",
        entityColumn = "song_id",
        associateBy = Junction(SongArtistCrossRefEntity::class)
    )
    val songs: List<SongEntity>
)
