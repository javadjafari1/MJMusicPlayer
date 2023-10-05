package ir.thatsmejavad.mjmusic.di

import ir.thatsmejavad.mjmusic.MainViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { MainViewModel() }
}
