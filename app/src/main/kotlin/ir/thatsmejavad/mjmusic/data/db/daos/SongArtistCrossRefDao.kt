package ir.thatsmejavad.mjmusic.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ir.thatsmejavad.mjmusic.data.db.entites.SongArtistCrossRefEntity

@Dao
interface SongArtistCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<SongArtistCrossRefEntity>)
}
