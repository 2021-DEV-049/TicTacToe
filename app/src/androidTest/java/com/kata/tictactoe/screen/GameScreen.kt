package com.kata.tictactoe.screen

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kata.tictactoe.R
import io.github.kakaocup.kakao.dialog.KAlertDialog
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

class GameScreen : Screen<GameScreen>() {

    val player1Label = KTextView { withId(R.id.player1Name) }

    val player2Label = KTextView { withId(R.id.player2Name) }

    val alertDialog = KAlertDialog()

    val resetBtn = KButton { withId(R.id.reset_btn) }

    val gameRecyclerView = KRecyclerView(builder = { withId(R.id.gameRecyclerView) },
        itemTypeBuilder = { itemType(::Item) })

    class Item(parent: Matcher<View>) :
        KRecyclerItem<Item>(parent) {
        val itemView = KButton(parent) { withId(R.id.item_btn) }
    }
}