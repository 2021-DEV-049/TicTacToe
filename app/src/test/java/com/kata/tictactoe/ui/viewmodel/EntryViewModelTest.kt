package com.kata.tictactoe.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.kata.tictactoe.getOrAwaitValue
import org.junit.Rule
import org.junit.Test

class EntryViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val viewModel = EntryViewModel()

    @Test
    fun test_should_return_false_if_one_of_the_field_is_empty() {

        viewModel.checkIfFieldsAreValid("Player1", "")

        val result = viewModel.areFieldsValid.getOrAwaitValue()

        assertThat(result).isFalse()
    }
}