package com.kata.tictactoe

import android.app.Application
import com.kata.tictactoe.ui.viewmodel.EntryViewModel
import com.kata.tictactoe.ui.viewmodel.GameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GameApplication)
            modules(listOf(appModule, viewModelModule))
        }
    }
}

val appModule = module {
    factory { Board() }
}

val viewModelModule = module {
    viewModel { EntryViewModel() }
    viewModel { GameViewModel(get()) }
}