package com.kata.tictactoe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EntryViewModel : ViewModel() {

    private var _areFieldsValid = MutableLiveData<Boolean>()
    val areFieldsValid: LiveData<Boolean> = _areFieldsValid

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