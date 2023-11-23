package ir.thatsmejavad.mjmusic.di

import androidx.media3.exoplayer.ExoPlayer
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioHandler
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val audioPlayerModule = module {
    single {
        ExoPlayer.Builder(androidContext())
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    single {
        AudioHandler(get())
    }
}
