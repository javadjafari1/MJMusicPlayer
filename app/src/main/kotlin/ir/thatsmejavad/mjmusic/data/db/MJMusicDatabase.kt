package ir.thatsmejavad.mjmusic.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.thatsmejavad.mjmusic.data.db.entites.AlbumEntity
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.GenreEntity
import ir.thatsmejavad.mjmusic.data.db.entites.PlaylistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

@Database(
    entities = [
        SongEntity::class,
        ArtistEntity::class,
        AlbumEntity::class,
        GenreEntity::class,
        PlaylistEntity::class
    ],
    version = 1
)
abstract class MJMusicDatabase : RoomDatabase() {
}