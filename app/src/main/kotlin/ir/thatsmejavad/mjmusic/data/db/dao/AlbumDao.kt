package ir.thatsmejavad.mjmusic.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.thatsmejavad.mjmusic.data.db.entites.AlbumEntity
import ir.thatsmejavad.mjmusic.data.db.relations.AlbumWithSongs

@Dao
interface AlbumDao {

    @Insert(AlbumEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(songs: List<AlbumEntity>)

    @Query("SELECT * FROM albums")
    suspend fun getAlbumsWithSongs(): List<AlbumWithSongs>
}
