package com.kata.tictactoe.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.kata.tictactoe.Board
import com.kata.tictactoe.utils.GameState
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {

    private val mockedBoard: Board = mockk()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun should_update_live_data_when_board_is_updated() = runBlocking {
        every { mockedBoard.getCurrentPlayerTurn() } returns 0
        every { mockedBoard.cellsValue } returns mutableListOf("X", "O")
        every { mockedBoard.updateCellsValue(any()) } returns Unit
        every { mockedBoard.hasWinner() } returns true
        every { mockedBoard.hasATie() } returns false

        val viewModel = GameViewModel(mockedBoard)
        val gameStateObserver = spyk<Observer<GameState>>()
        viewModel.gameState.observeForever(gameStateObserver)

        viewModel.initializePlayerNames(player1 = Player1, player2 = Player2)
        viewModel.updateBoard(0)

        dispatcher.advanceUntilIdle()

        verifyOrder {
            gameStateObserver.onChanged(GameState.InProgress(0, "X"))
            gameStateObserver.onChanged(GameState.Result(Player_Winning_Msg))
        }
    }

    @Test
    fun should_reset_board_values_to_initial_state() {
        val board = Board()
        val viewModel = GameViewModel(board)

        viewModel.resetBoard()

        assertThat(board.cellsValue)
            .isEqualTo(listOf("0", "0", "0", "0", "0", "0", "0", "0", "0"))
        assertThat(board.playerTurn).isEqualTo(0)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object {
        const val Player1 = "Avatar1"
        const val Player2 = "Avatar2"
        const val Player_Winning_Msg = "Avatar1 Won the game!"
    }
}