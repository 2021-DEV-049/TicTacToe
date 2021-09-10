package com.kata.tictactoe.ui.fragments

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
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
                    typeText(name1)
                    hasText(name1)
                }
            }

            tilPlayer2 {
                isDisplayed()
                isEnabled()
                hasHint(R.string.player2_hint)
                edit {
                    typeText(name2)
                    hasText(name2)
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

    @Test
    fun should_display_snack_bar_error_message_when_one_of_the_field_is_empty() {
        onScreen<EntryScreen> {
            tilPlayer1 {
                isDisplayed()
                isEnabled()
                hasHint(R.string.player1_hint)
                edit {
                    typeText(name1)
                    hasText(name1)
                }
            }

            closeSoftKeyboard()

            playBtn {
                isDisplayed()
                isClickable()
                hasText(R.string.entry_title_label)
                click()
            }

            snackBar {
                isDisplayed()
                hasBackgroundColor(R.color.colorSnackBarError)
                hasDescendant {
                    withText(R.string.enter_all_fields)
                }
            }
        }
    }

    @Test
    fun should_navigate_to_next_screen_when_all_fields_are_valid() {
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
                    typeText(name1)
                    hasText(name1)
                }
            }

            tilPlayer2 {
                isDisplayed()
                isEnabled()
                hasHint(R.string.player2_hint)
                edit {
                    typeText(name2)
                    hasText(name2)
                }
            }

            playBtn {
                isDisplayed()
                isClickable()
                hasText(R.string.entry_title_label)
                click()
            }
        }
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.gameFragment)
    }

    @Test
    fun should_not_be_able_to_enter_characters_more_than_12() {
        onScreen<EntryScreen> {
            tilPlayer1 {
                edit {
                    typeText("charactergreaterthan12")
                    hasText("charactergre")
                }
            }
            tilPlayer2 {
                edit {
                    typeText("charactergreaterthan12")
                    hasText("charactergre")
                }
            }
        }
    }

    companion object {
        private const val name1 = "Name1"
        private const val name2 = "Name2"
    }
}