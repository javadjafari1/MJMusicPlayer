package ir.thatsmejavad.mjmusic.di

import ir.thatsmejavad.mjmusic.MainActivityViewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    single { MainActivityViewModel() }
}
