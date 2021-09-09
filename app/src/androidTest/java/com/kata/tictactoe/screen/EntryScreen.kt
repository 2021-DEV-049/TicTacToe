package com.kata.tictactoe.screen

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kata.tictactoe.R
import io.github.kakaocup.kakao.edit.KTextInputLayout
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KSnackbar
import io.github.kakaocup.kakao.text.KTextView

class EntryScreen : Screen<EntryScreen>() {

    val headerLabel = KTextView { withId(R.id.main_label) }

    val descriptionLabel = KTextView { withId(R.id.desc_label) }

    val tilPlayer1 = KTextInputLayout { withId(R.id.til_player1) }

    val tilPlayer2 = KTextInputLayout { withId(R.id.til_player2) }

    val playBtn = KButton { withId(R.id.play_btn) }

    val snackBar = KSnackbar()
}