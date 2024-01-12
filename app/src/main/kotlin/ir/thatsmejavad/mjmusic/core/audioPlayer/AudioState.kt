package ir.thatsmejavad.mjmusic.core.audioPlayer

import ir.thatsmejavad.mjmusic.enums.RepeatMode

data class AudioState(
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val isShuffleModeEnabled: Boolean = false,
    val currentSongIndex: Int = -1,
    val currentSongPosition: Long = 0,
    val playBackSpeed: Float = 1f,
    val volume: Float = .5f,
    val repeatMode: RepeatMode = RepeatMode.REPEAT_MODE_OFF
)
