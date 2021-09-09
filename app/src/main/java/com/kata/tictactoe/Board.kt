package com.kata.tictactoe

class Board {

    var playerTurn = 0

    var cellsValue = mutableListOf<String>()

    init {
        for (r in INDEX_0..INDEX_8) {
            cellsValue.add(VALUE_0)
        }
    }

    fun getCurrentPlayerTurn(): Int {
        return playerTurn
    }

    fun updatePlayerTurn() {
        if (playerTurn == INDEX_0)
            playerTurn++
        else
            playerTurn = INDEX_0
    }

    fun updateCellsValue(position: Int) {
        if (getCurrentPlayerTurn() == INDEX_0) {
            cellsValue[position] = VALUE_X
        }
        if (getCurrentPlayerTurn() == INDEX_1) {
            cellsValue[position] = VALUE_O
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
            cellsValue[it[INDEX_0]] == cellsValue[it[INDEX_1]]
                    && cellsValue[it[INDEX_0]] == cellsValue[it[INDEX_2]]
                    && cellsValue[it[0]] != VALUE_0
        }
    }

    fun hasATie(): Boolean {
        for (cell in cellsValue) {
            if (cell.isEmpty() || cell == VALUE_0)
                return false
        }
        return true
    }

    companion object {
        const val VALUE_0 = "0"
        const val VALUE_X = "X"
        const val VALUE_O = "O"
        const val INDEX_0 = 0
        const val INDEX_1 = 1
        const val INDEX_2 = 2
        const val INDEX_8 = 8
    }
}