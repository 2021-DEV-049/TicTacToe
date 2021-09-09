package com.kata.tictactoe

class Board {

    var playerTurn = 0

    var cellsValue = mutableListOf<String>()

    init {
        for (r in 0..8) {
            cellsValue.add("0")
        }
    }

    fun getCurrentPlayerTurn(): Int {
        return playerTurn
    }

    fun updatePlayerTurn() {
        if (playerTurn == 0)
            playerTurn++
        else
            playerTurn = 0
    }

    fun updateCellsValue(position: Int) {
        if (getCurrentPlayerTurn() == 0) {
            cellsValue[position] = "X"
        }
        if (getCurrentPlayerTurn() == 1) {
            cellsValue[position] = "O"
        }
        updatePlayerTurn()
    }
}