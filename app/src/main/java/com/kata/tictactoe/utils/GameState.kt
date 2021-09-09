package com.kata.tictactoe.utils

sealed class GameState {
    data class InProgress(val cellPosition: Int, val cellValue: String) : GameState()
    data class Result(val message: String) : GameState()
}
