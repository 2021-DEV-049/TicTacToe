package com.kata.tictactoe.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

class EntryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = EntryViewModel()
}