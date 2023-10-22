package ir.thatsmejavad.mjmusic.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ir.thatsmejavad.mjmusic.data.db.entites.ArtistEntity
import ir.thatsmejavad.mjmusic.data.db.relations.ArtistsWithSongs

@Dao
interface ArtistDao {

    @Insert
    suspend fun insertArtists(artists: List<ArtistEntity>)

    @Transaction
    @Query("SELECT * FROM artists")
    suspend fun getArtistsWithSongs(): List<ArtistsWithSongs>
}
