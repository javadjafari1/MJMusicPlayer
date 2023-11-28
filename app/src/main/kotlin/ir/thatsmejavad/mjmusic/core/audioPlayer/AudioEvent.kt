package ir.thatsmejavad.mjmusic.core.audioPlayer

sealed interface AudioEvent {
    data object PlayPause : AudioEvent
    data object Stop : AudioEvent
    data object Forward : AudioEvent
    data object Backward : AudioEvent
    data object SeekToNext : AudioEvent
    data object SeekToPrevious : AudioEvent
    data class UpdateProgress(val progressMs: Long) : AudioEvent
    data class SeekTo(val mediaItemIndex: Int, val positionMs: Long = 0) : AudioEvent
}
