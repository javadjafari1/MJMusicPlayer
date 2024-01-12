package ir.thatsmejavad.mjmusic.core.audioPlayer

import androidx.annotation.FloatRange
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity
import ir.thatsmejavad.mjmusic.enums.RepeatMode
import kotlinx.coroutines.flow.StateFlow

interface AudioHandler {

    val audioState: StateFlow<AudioState>
    val nowPlayingSongs: StateFlow<List<SongEntity>>

    suspend fun setSong(song: SongEntity)
    suspend fun setSongs(songs: List<SongEntity>)

    suspend fun addSongToEnd(song: SongEntity)
    suspend fun addSongsToEnd(songs: List<SongEntity>)

    suspend fun addSongToNext(song: SongEntity)
    suspend fun addSongsToNext(songs: List<SongEntity>)

    suspend fun removeSong(index: Int)
    suspend fun removeSongs(fromIndex: Int, toIndex: Int)
    suspend fun removeAllSongs()

    fun release()

    fun setPlaybackSpeed(
        @FloatRange(
            from = 0.25,
            to = 2.0,
            fromInclusive = false
        ) speed: Float
    )

    fun setRepeatMode(repeatMode: RepeatMode)

    fun setIsShuffleModeEnabled(shuffleModeEnabled: Boolean)

    fun setVolume(@FloatRange(from = 0.0, to = 1.0) volume: Float)

    fun sendEvent(audioEvent: AudioEvent)
}
