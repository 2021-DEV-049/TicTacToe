package com.kata.tictactoe

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class BoardTest {
    private lateinit var board: Board

    @Before
    fun setUp() {
        board = Board()
    }

    /*
    * 0 represents turn for player1
    * 1 represents turn for player2
    * */
    @Test
    fun check_if_the_turn_belongs_to_player_1() {
        val expectedResult = 0

        val result = board.getCurrentPlayerTurn()

        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_if_the_turn_belongs_to_player_2() {
        val expectedResult = 1

        board.updatePlayerTurn()
        val result = board.getCurrentPlayerTurn()

        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun initial_board_state_should_contain_zeros() {

        val expectedValue = getInitialBoardState()

        assertThat(expectedValue).isEqualTo(board.cellsValue)

    }

    private fun getInitialBoardState(): List<String> {
        val gameBoard = mutableListOf<String>()
        for (r in 0..8) {
            gameBoard.add("0")
        }
        return gameBoard
    }
}