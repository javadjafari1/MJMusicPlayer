package ir.thatsmejavad.mjmusic.core.audioPlayer

import androidx.annotation.FloatRange
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class AudioHandler(private val player: ExoPlayer) {
    init {
        player.prepare()
    }

    fun addMediaItem(mediaItem: MediaItem) = player.addMediaItem(mediaItem)
    fun addMediaItems(mediaItems: List<MediaItem>) = player.addMediaItems(mediaItems)


    fun removeMediaItem(index: Int) = player.removeMediaItem(index)
    fun removeMediaItems(fromIndex: Int, toIndex: Int) = player.removeMediaItems(fromIndex, toIndex)


    fun setMediaItem(mediaItem: MediaItem) = player.setMediaItem(mediaItem)

    fun setMediaItems(mediaItems: List<MediaItem>) = player.setMediaItems(mediaItems)


    fun setPlaybackSpeed(@FloatRange(from = 0.0, to = 2.0, fromInclusive = false) speed: Float) =
        player.setPlaybackSpeed(speed)

    fun setVolume(@FloatRange(from = 0.0, to = 1.0) volume: Float) {
        player.volume = volume
    }

    fun getVolume() = player.volume


    fun setEvent(audioEvent: AudioEvent) {
        when (audioEvent) {
            AudioEvent.PlayPause -> handlePlayPauseEvent()
            AudioEvent.Stop -> player.stop()
            AudioEvent.Backward -> player.seekBack()
            AudioEvent.Forward -> player.seekForward()
            AudioEvent.SeekToNext -> player.seekToNextMediaItem()
            AudioEvent.SeekToPrevious -> player.seekToPreviousMediaItem()
            is AudioEvent.UpdateProgress -> player.seekTo(audioEvent.progressMs)
            is AudioEvent.SeekTo -> player.seekTo(
                audioEvent.mediaItemIndex,
                audioEvent.positionMs
            )
        }
    }

    private fun handlePlayPauseEvent() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
    }
}
