package ir.thatsmejavad.mjmusic.di

import ir.thatsmejavad.mjmusic.main.MainViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    single { MainViewModel() }
}
