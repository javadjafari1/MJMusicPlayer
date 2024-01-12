package ir.thatsmejavad.mjmusic.di

import androidx.media3.exoplayer.ExoPlayer
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioHandler
import ir.thatsmejavad.mjmusic.core.audioPlayer.AudioHandlerImpl
import ir.thatsmejavad.mjmusic.core.contentProvider.provider.AudioProvider
import ir.thatsmejavad.mjmusic.core.contentProvider.provider.AudioProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val audioModule = module {
    single {
        ExoPlayer.Builder(androidContext())
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    single { AudioHandlerImpl(get()) } bind AudioHandler::class
    single { AudioProviderImpl() } bind AudioProvider::class
}
