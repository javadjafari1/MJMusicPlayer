package ir.thatsmejavad.mjmusic.core.audioPlayer

import androidx.annotation.FloatRange
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import ir.thatsmejavad.mjmusic.data.db.entites.SongEntity
import ir.thatsmejavad.mjmusic.enums.RepeatMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AudioHandlerImpl(private val player: ExoPlayer) : AudioHandler {
    init {
        player.prepare()
        setupListeners()
    }

    companion object {
        private const val PROGRESS_UPDATE_INTERVAL = 1000L
        private const val BACKWARD_THRESHOLD = 3000L
    }

    private val _audioState = MutableStateFlow(AudioState())
    override val audioState = _audioState.asStateFlow()

    private val _nowPlayingSongs = MutableStateFlow<List<SongEntity>>(emptyList())
    override val nowPlayingSongs = _nowPlayingSongs.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var progressJob: Job? = null


    private fun setupListeners() {
        player.addListener(object : Player.Listener {

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                mediaItem?.let {
                    _audioState.update {
                        it.copy(
                            currentSongIndex = player.currentMediaItemIndex,
                            currentSongPosition = player.currentPosition
                        )
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                _audioState.update { it.copy(isPlaying = isPlaying) }
                if (isPlaying) {
                    startProgressUpdate()
                } else {
                    stopProgressUpdater()
                }
            }

            override fun onVolumeChanged(volume: Float) {
                super.onVolumeChanged(volume)
                _audioState.update { it.copy(volume = volume) }
            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
                super.onPlaybackParametersChanged(playbackParameters)
                _audioState.update { it.copy(playBackSpeed = playbackParameters.speed) }
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                super.onIsLoadingChanged(isLoading)
                _audioState.update { it.copy(isLoading = isLoading) }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                super.onRepeatModeChanged(repeatMode)
                _audioState.update { it.copy(repeatMode = RepeatMode.getFromValue(repeatMode)) }
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                super.onShuffleModeEnabledChanged(shuffleModeEnabled)
                _audioState.update { it.copy(isShuffleModeEnabled = shuffleModeEnabled) }
            }
        }
        )
    }

    private fun startProgressUpdate() {
        progressJob = coroutineScope.launch {
            while (_audioState.value.isPlaying) {
                _audioState.update { it.copy(currentSongPosition = player.currentPosition) }
                delay(PROGRESS_UPDATE_INTERVAL)
            }
        }
    }

    private fun stopProgressUpdater() {
        progressJob?.cancel()
    }

    override suspend fun setSong(song: SongEntity) {
        _nowPlayingSongs.emit(listOf(song))
        player.setMediaItem(song.toMediaItem())
    }

    override suspend fun setSongs(songs: List<SongEntity>) {
        _nowPlayingSongs.emit(songs)
        player.setMediaItems(songs.map { it.toMediaItem() })
    }

    override suspend fun addSongToEnd(song: SongEntity) {
        _nowPlayingSongs.emit(_nowPlayingSongs.value + song)
        player.addMediaItem(song.toMediaItem())
    }

    override suspend fun addSongsToEnd(songs: List<SongEntity>) {
        _nowPlayingSongs.emit(_nowPlayingSongs.value + songs)
        player.addMediaItems(songs.map { it.toMediaItem() })
    }

    override suspend fun addSongToNext(song: SongEntity) {
        if (_nowPlayingSongs.value.isEmpty()) {
            setSong(song)
        } else {
            val currentSongIndex = _audioState.value.currentSongIndex
            _nowPlayingSongs.emit(
                _nowPlayingSongs.value.toMutableList().apply { add(currentSongIndex + 1, song) }
            )
            player.addMediaItem(currentSongIndex + 1, song.toMediaItem())
        }
    }

    override suspend fun addSongsToNext(songs: List<SongEntity>) {
        if (_nowPlayingSongs.value.isEmpty()) {
            setSongs(songs)
        } else {
            val currentSongIndex = _audioState.value.currentSongIndex
            _nowPlayingSongs.emit(
                _nowPlayingSongs.value.toMutableList().apply { addAll(currentSongIndex + 1, songs) }
            )
            player.addMediaItems(currentSongIndex + 1, songs.map { it.toMediaItem() })
        }
    }

    override suspend fun removeSong(index: Int) {
        _nowPlayingSongs.emit(_nowPlayingSongs.value.toMutableList().apply { removeAt(index) })
        player.removeMediaItem(index)
    }

    override suspend fun removeSongs(fromIndex: Int, toIndex: Int) {
        _nowPlayingSongs.emit(
            _nowPlayingSongs.value.toMutableList().apply { subList(fromIndex, toIndex).clear() }
        )
        player.removeMediaItems(fromIndex, toIndex)
    }

    override suspend fun removeAllSongs() {
        _nowPlayingSongs.emit(emptyList())
        player.clearMediaItems()
    }

    override fun release() {
        player.release()
        coroutineScope.cancel()
    }

    override fun setPlaybackSpeed(
        @FloatRange(
            from = 0.25,
            to = 2.0
        ) speed: Float
    ) = player.setPlaybackSpeed(speed)

    override fun setRepeatMode(repeatMode: RepeatMode) {
        player.repeatMode = repeatMode.value
    }

    override fun setIsShuffleModeEnabled(shuffleModeEnabled: Boolean) {
        player.shuffleModeEnabled = shuffleModeEnabled
    }

    override fun setVolume(@FloatRange(from = 0.0, to = 1.0) volume: Float) {
        player.volume = volume
    }

    override fun sendEvent(audioEvent: AudioEvent) {
        when (audioEvent) {
            AudioEvent.PlayPause -> handlePlayPauseEvent()
            AudioEvent.Stop -> handleStopEvent()
            AudioEvent.Backward -> backward()
            AudioEvent.Forward -> forward()
            AudioEvent.SeekToNext -> seekToNext()
            AudioEvent.SeekToPrevious -> seekToPrevious()
            is AudioEvent.SeekToPosition -> seekToPosition(audioEvent.positionMs)
            is AudioEvent.SeekToItem -> seekToItem(
                index = audioEvent.index,
                positionMs = audioEvent.positionMs
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

    private fun handleStopEvent() {
        player.pause()
        player.seekTo(0)
        _audioState.update { it.copy(currentSongPosition = 0) }
    }

    private fun forward() {
        if (player.isPlaying) {
            player.seekForward()
        }
    }

    private fun backward() {
        if (player.isPlaying) {
            player.seekBack()
        }
    }

    private fun seekToNext() {
        player.seekToNextMediaItem()
    }

    private fun seekToPrevious() {
        if (player.currentPosition > BACKWARD_THRESHOLD) {
            player.seekTo(player.currentMediaItemIndex, 0)
            _audioState.update { it.copy(currentSongPosition = 0) }
        } else {
            player.seekToPreviousMediaItem()
        }
    }

    private fun seekToPosition(positionMs: Long) {
        player.seekTo(positionMs)
        _audioState.update { it.copy(currentSongPosition = positionMs) }
    }

    private fun seekToItem(index: Int, positionMs: Long) {
        player.seekTo(index, positionMs)
    }
}
