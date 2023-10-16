package ir.thatsmejavad.mjmusic.di

import ir.thatsmejavad.mjmusic.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    includes(databaseModule)

    viewModelOf(::MainViewModel)
}
