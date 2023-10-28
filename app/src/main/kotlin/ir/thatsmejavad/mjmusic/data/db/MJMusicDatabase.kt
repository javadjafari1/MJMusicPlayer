package ir.thatsmejavad.mjmusic.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.thatsmejavad.mjmusic.data.db.daos.AlbumDao
import ir.thatsmejavad.mjmusic.data.db.daos.ArtistDao
import ir.thatsmejavad.mjmusic.data.db.daos.SongArtistCrossRefDao
import ir.thatsmejavad.mjmusic.data.db.daos.SongDao
import ir.thatsmejavad.mjmusic.data.db.entites.AlbumEntity
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.GenreEntity
import ir.thatsmejavad.mjmusic.data.db.entites.PlaylistEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity

@Database(
    entities = [
        SongEntity::class,
        ArtistEntity::class,
        AlbumEntity::class,
        GenreEntity::class,
        PlaylistEntity::class,
        SongArtistCrossRefEntity::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class MJMusicDatabase : RoomDatabase() {
    abstract fun getSongDao(): SongDao
    abstract fun getAlbumDao(): AlbumDao
    abstract fun getArtistsDao(): ArtistDao
    abstract fun getSongArtistCrossRefDao(): SongArtistCrossRefDao
}
