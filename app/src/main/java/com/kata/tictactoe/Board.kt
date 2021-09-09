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
        val winningCombinations = getWinningCombination()
        return (winningCombinations != null && winningCombinations.isNotEmpty())
    }

    /*
   * checks if any combinations is found from given cell combinations
   * firstOrNull returns the array combination if a match is found
   * */
    private fun getWinningCombination(): IntArray? {
        val winningCombinations = listOf(
            //horizontal combinations
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            //Vertical combinations
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            //diagonal combinations
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )
        return winningCombinations.firstOrNull {
            cellsValue[it[0]] == cellsValue[it[1]] && cellsValue[it[0]] == cellsValue[it[2]]
                    && cellsValue[it[0]] != "0"
        }
    }
}