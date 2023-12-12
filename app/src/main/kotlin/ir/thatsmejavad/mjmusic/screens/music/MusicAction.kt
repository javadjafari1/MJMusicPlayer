package ir.thatsmejavad.mjmusic.screens.music

import ir.thatsmejavad.mjmusic.core.contentProvider.model.AudioColumns

sealed class MusicAction {
    data class OnAudioSelect(val song: AudioColumns) : MusicAction()
}
