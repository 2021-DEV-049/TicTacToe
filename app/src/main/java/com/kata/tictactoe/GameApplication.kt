package com.kata.tictactoe

import android.app.Application
import com.kata.tictactoe.ui.viewmodel.EntryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GameApplication)
            modules(listOf(viewModelModule))
        }
    }
}

val viewModelModule = module {
    viewModel { EntryViewModel() }
}