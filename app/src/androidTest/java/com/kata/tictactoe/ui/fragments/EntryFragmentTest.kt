package com.kata.tictactoe.ui.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.kata.tictactoe.R
import com.kata.tictactoe.screen.EntryScreen
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import org.junit.Before
import org.junit.Test


class EntryFragmentTest {

    private lateinit var navController: TestNavHostController
    private lateinit var scenario: FragmentScenario<EntryFragment>

    @Before
    fun setUp() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        scenario = launchFragmentInContainer(themeResId = R.style.TicTacToe)
        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun checkIfViewsAreVisibleAndTextsAreDisplayed() {

        onScreen<EntryScreen> {
            headerLabel {
                isDisplayed()
                hasText(R.string.entry_title_label)
            }

            descriptionLabel {
                isDisplayed()
                hasText(R.string.enter_your_name)
            }

            tilPlayer1 {
                isDisplayed()
                isEnabled()
                hasHint(R.string.player1_hint)
                edit {
                    typeText("Name1")
                    hasText("Name1")
                }
            }

            tilPlayer2 {
                isDisplayed()
                isEnabled()
                hasHint(R.string.player2_hint)
                edit {
                    typeText("Name2")
                    hasText("Name2")
                }
            }

            playBtn {
                isDisplayed()
                isClickable()
                hasText(R.string.entry_title_label)
                click()
            }
        }
    }
}