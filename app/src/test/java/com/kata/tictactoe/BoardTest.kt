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

    @Test
    fun test_first_value_in_the_board_should_be_set_to_X() {
        val expectedResult = "X"

        board.updateCellsValue(0)
        val result = board.cellsValue[0]

        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_second_value_in_the_board_should_be_set_to_O() {
        val expectedResult = "O"

        board.updatePlayerTurn()
        board.updateCellsValue(1)
        val result = board.cellsValue[1]

        assertThat(expectedResult).isEqualTo(result)
    }

    @Test
    fun test_if_alternate_value_is_set_for_players() {
        val expectedResult = listOf("X", "O", "X", "O", "X", "O", "X", "O", "X")

        for (i in 0..8) {
            board.updateCellsValue(i)
        }

        assertThat(expectedResult).isEqualTo(board.cellsValue)
    }

    /* combination
      X X X
      O O
      */
    @Test
    fun test_if_player1_can_win_if_first_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(3)
        board.updateCellsValue(2)
        board.updateCellsValue(4)
        board.updateCellsValue(1)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
      O O O
      X X O
      X X
      */
    @Test
    fun test_if_player2_can_win_if_first_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(3)
        board.updateCellsValue(0)
        board.updateCellsValue(4)
        board.updateCellsValue(5)
        board.updateCellsValue(6)
        board.updateCellsValue(1)
        board.updateCellsValue(7)
        board.updateCellsValue(2)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
      O O 0
      X X X
      0 0 0
     */
    @Test
    fun test_if_player1_can_win_if_second_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(3)
        board.updateCellsValue(0)
        board.updateCellsValue(4)
        board.updateCellsValue(1)
        board.updateCellsValue(5)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
       X X 0
       O O O
       X X 0
      */
    @Test
    fun test_if_player2_can_win_if_second_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(3)
        board.updateCellsValue(1)
        board.updateCellsValue(2)
        board.updateCellsValue(6)
        board.updateCellsValue(4)
        board.updateCellsValue(7)
        board.updateCellsValue(5)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
      O O 0
      0 0 0
      X X X
     */
    @Test
    fun test_if_player1_can_win_if_third_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(6)
        board.updateCellsValue(0)
        board.updateCellsValue(7)
        board.updateCellsValue(1)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
    X X 0
    X 0 0
    O O O
   */
    @Test
    fun test_if_player2_can_win_if_third_horizontal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(6)
        board.updateCellsValue(1)
        board.updateCellsValue(7)
        board.updateCellsValue(3)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
    X O 0
    X O 0
    X 0 0
   */
    @Test
    fun test_if_player1_can_win_if_first_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(1)
        board.updateCellsValue(3)
        board.updateCellsValue(4)
        board.updateCellsValue(6)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
        O X X
        O X 0
        O 0 0
       */
    @Test
    fun test_if_player2_can_win_if_first_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(1)
        board.updateCellsValue(0)
        board.updateCellsValue(2)
        board.updateCellsValue(3)
        board.updateCellsValue(4)
        board.updateCellsValue(6)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
       O X 0
       O X 0
       0 X 0
       */
    @Test
    fun test_if_player1_can_win_if_second_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(1)
        board.updateCellsValue(0)
        board.updateCellsValue(4)
        board.updateCellsValue(3)
        board.updateCellsValue(7)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
     X O X
     X O 0
     0 O 0
     */
    @Test
    fun test_if_player2_can_win_if_second_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(1)
        board.updateCellsValue(2)
        board.updateCellsValue(4)
        board.updateCellsValue(3)
        board.updateCellsValue(7)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
         O 0 X
         O 0 X
         0 0 X
         */
    @Test
    fun test_if_player1_can_win_if_third_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(2)
        board.updateCellsValue(0)
        board.updateCellsValue(5)
        board.updateCellsValue(3)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
   X X O
   X 0 O
   0 0 O
   */
    @Test
    fun test_if_player2_can_win_if_third_vertical_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(2)
        board.updateCellsValue(1)
        board.updateCellsValue(5)
        board.updateCellsValue(3)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
    X O 0
    O X 0
    0 0 X
    */
    @Test
    fun test_if_player1_can_win_if_left_diagonal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(1)
        board.updateCellsValue(4)
        board.updateCellsValue(3)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
    O X 0
    X O 0
    X 0 O
    */
    @Test
    fun test_if_player2_can_win_if_left_diagonal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(1)
        board.updateCellsValue(0)
        board.updateCellsValue(3)
        board.updateCellsValue(4)
        board.updateCellsValue(6)
        board.updateCellsValue(8)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
   O O X
   0 X 0
   X 0 0
   */
    @Test
    fun test_if_player1_can_win_if_right_diagonal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(2)
        board.updateCellsValue(0)
        board.updateCellsValue(4)
        board.updateCellsValue(1)
        board.updateCellsValue(6)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    /* combination
     X X O
     X O 0
     O 0 0
     */
    @Test
    fun test_if_player2_can_win_if_right_diagonal_combination_is_found() {
        val expectedResult = true

        board.updateCellsValue(0)
        board.updateCellsValue(2)
        board.updateCellsValue(1)
        board.updateCellsValue(4)
        board.updateCellsValue(3)
        board.updateCellsValue(6)

        val winner = board.hasWinner()

        assertThat(expectedResult).isEqualTo(winner)
    }

    private fun getInitialBoardState(): List<String> {
        val gameBoard = mutableListOf<String>()
        for (r in 0..8) {
            gameBoard.add("0")
        }
        return gameBoard
    }
}