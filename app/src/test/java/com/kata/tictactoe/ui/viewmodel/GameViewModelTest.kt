package com.kata.tictactoe.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kata.tictactoe.Board
import com.kata.tictactoe.utils.GameState
import io.mockk.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GameViewModelTest {

    private val mockedBoard: Board = mockk()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun should_update_live_data_when_board_is_updated() {
        every { mockedBoard.getCurrentPlayerTurn() } returns 0
        every { mockedBoard.cellsValue } returns mutableListOf("X", "O")
        every { mockedBoard.updateCellsValue(any()) } returns Unit
        every { mockedBoard.hasWinner() } returns true
        every { mockedBoard.hasATie() } returns false

        val viewModel = GameViewModel(mockedBoard)
        val gameStateObserver = spyk<Observer<GameState>>()
        viewModel.gameState.observeForever(gameStateObserver)

        viewModel.initializePlayerNames(player1 = "Player1", player2 = "Player2")
        viewModel.updateBoard(0)

        verifyOrder {
            gameStateObserver.onChanged(GameState.InProgress(0, "X"))
            gameStateObserver.onChanged(GameState.Result("Player1 Won the game!"))
        }
    }
}