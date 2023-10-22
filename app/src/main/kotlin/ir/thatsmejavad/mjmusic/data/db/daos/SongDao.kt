package ir.thatsmejavad.mjmusic.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity
import ir.thatsmejavad.mjmusic.data.db.relations.SongWithArtists

@Dao
interface SongDao {
    @Insert(SongEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Transaction
    @Query("SELECT * FROM songs")
    suspend fun getSongsWithArtists(): List<SongWithArtists>
}
