package com.kata.tictactoe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kata.tictactoe.Board
import com.kata.tictactoe.Board.Companion.INDEX_0
import com.kata.tictactoe.Board.Companion.INDEX_8
import com.kata.tictactoe.Board.Companion.VALUE_0
import com.kata.tictactoe.utils.GameState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(private val board: Board) : ViewModel() {

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = _gameState

    private var playerTurn = 0
    private var playerNames = arrayListOf<String>()

    private var isGameInProgress = true

    fun initializePlayerNames(player1: String, player2: String) {
        playerNames.add(player1)
        playerNames.add(player2)
    }

    fun updateBoard(position: Int) {
        if (!isGameInProgress) {
            return
        }
        playerTurn = board.getCurrentPlayerTurn()
        board.updateCellsValue(position)
        postGameStateInProgress(position)
        checkForWinner()
    }

    private fun postGameStateInProgress(position: Int) {
        _gameState.postValue(
            GameState.InProgress(
                cellPosition = position,
                cellValue = board.cellsValue[position]
            )
        )
    }

    private fun checkForWinner() {
        val hasWinner = board.hasWinner()
        val isTied = board.hasATie()
        when {
            hasWinner -> {
                val winnerName = playerNames[playerTurn]
                postGameStateResult("$winnerName $WINNER_MSG")
            }
            isTied -> {
                postGameStateResult(TIE_MSG)
            }
        }
    }

    private fun postGameStateResult(message: String) {
        isGameInProgress = false
        viewModelScope.launch {
            delay(30L)
            _gameState.postValue(GameState.Result(message))
        }
    }

    fun resetBoard() {
        board.cellsValue.clear()
        for (i in INDEX_0..INDEX_8) {
            board.cellsValue.add(VALUE_0)
        }
        board.playerTurn = INDEX_0
        isGameInProgress = true
    }

    companion object {
        const val WINNER_MSG = "Won the game!"
        const val TIE_MSG = "game is a tie!"
    }
}