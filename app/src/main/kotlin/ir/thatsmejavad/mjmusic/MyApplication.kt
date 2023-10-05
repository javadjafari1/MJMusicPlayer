package ir.thatsmejavad.mjmusic

import android.app.Application
import ir.thatsmejavad.mjmusic.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModule)
        }
    }
}
