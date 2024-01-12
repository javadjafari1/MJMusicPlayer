package ir.thatsmejavad.mjmusic

import android.app.Application
import ir.thatsmejavad.mjmusic.di.databaseModule
import ir.thatsmejavad.mjmusic.di.audioModule
import ir.thatsmejavad.mjmusic.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MJMusicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MJMusicApp)
            modules(
                viewModelsModule,
                databaseModule,
                audioModule
            )
        }
    }
}
