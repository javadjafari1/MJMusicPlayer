package ir.thatsmejavad.mjmusic.core.audioPlayer

import androidx.annotation.FloatRange
import androidx.media3.common.MediaItem
import androidx.media3.common.Player

interface AudioHandler : Player.Listener {
    fun addMediaItem(mediaItem: MediaItem)
    fun addMediaItems(mediaItems: List<MediaItem>)


    fun removeMediaItem(index: Int)
    fun removeMediaItems(fromIndex: Int, toIndex: Int)


    fun setMediaItem(mediaItem: MediaItem)
    fun setMediaItems(mediaItems: List<MediaItem>)


    fun setPlaybackSpeed(@FloatRange(from = 0.0, to = 2.0, fromInclusive = false) speed: Float)
    fun setVolume(@FloatRange(from = 0.0, to = 1.0) volume: Float)

    fun getVolume(): Float

    fun setEvent(audioEvent: AudioEvent)
}
