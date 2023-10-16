package ir.thatsmejavad.mjmusic.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity

@Dao
interface SongArtistCrossRefDao {

    @Insert
    suspend fun insert(list: List<SongArtistCrossRefEntity>)
}
