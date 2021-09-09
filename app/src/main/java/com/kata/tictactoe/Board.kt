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

    fun hasWinner(): Boolean {
        var hasWinner = false

        val horizontalCombination = getHorizontalCombination()
        when {
            horizontalCombination != null && horizontalCombination.isNotEmpty() -> {
                hasWinner = true
            }
        }
        return hasWinner
    }

    /*
   * checks if any horizontal combinations is found from given cell combinations
   * firstOrNull returns the array combination if a match is found
   * */
    private fun getHorizontalCombination(): IntArray? {
        val horizontalCombinations =
            listOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8))
        return horizontalCombinations.firstOrNull {
            cellsValue[it[0]] == cellsValue[it[1]] && cellsValue[it[0]] == cellsValue[it[2]]
                    && cellsValue[it[0]] != "0"
        }
    }
}