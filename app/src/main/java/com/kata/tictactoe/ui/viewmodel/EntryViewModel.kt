package com.kata.tictactoe.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.kata.tictactoe.utils.SingleLiveEvent

class EntryViewModel : ViewModel() {

    private var _areFieldsValid = SingleLiveEvent<Boolean>()
    val areFieldsValid get() = _areFieldsValid

    fun checkIfFieldsAreValid(player1Name: String, player2Name: String) {

        if ((player1Name.trim { it <= ' ' }).isBlank() ||
            (player2Name.trim { it <= ' ' }).isBlank()
        ) {
            _areFieldsValid.postValue(false)
        } else {
            _areFieldsValid.postValue(true)
        }
    }
}