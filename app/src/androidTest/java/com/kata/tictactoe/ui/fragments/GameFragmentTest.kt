package com.kata.tictactoe.ui.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.kata.tictactoe.R
import com.kata.tictactoe.screen.GameScreen
import io.github.kakaocup.kakao.screen.Screen.Companion.onScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class GameFragmentTest {

    private lateinit var navController: TestNavHostController

    private lateinit var scenario: FragmentScenario<GameFragment>

    private val fragmentArgs = bundleOf(
        player_1_name_key to player1Name,
        player_2_name_key to player2Name
    )

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        scenario = launchFragmentInContainer(fragmentArgs, themeResId = R.style.TicTacToe)

        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun text_view_should_display_names_from_args_bundle() {
        onScreen<GameScreen> {
            player1Label {
                isDisplayed()
                hasText(player1Name)
            }
            player2Label {
                isDisplayed()
                hasText(player2Name)
            }
        }
    }

    @Test
    fun should_display_recycler_view_with_expected_item_count() {

        onScreen<GameScreen> {
            gameRecyclerView {
                isDisplayed()
                hasSize(total_cells)
            }
        }
    }

    @Test
    fun recycler_view_items_should_be_clickable() {
        onScreen<GameScreen> {
            gameRecyclerView {
                for (i in 0 until total_cells) {
                    childAt<GameScreen.Item>(i) {
                        itemView {
                            isEnabled()
                            isClickable()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun should_show_player_won_the_game_alert() = runBlockingTest {

        onScreen<GameScreen> {
            gameRecyclerView {
                for (i in 0 until 7) {
                    childAt<GameScreen.Item>(i) {
                        itemView {
                            click()
                        }
                    }
                }
            }
            dispatcher.advanceUntilIdle()
            alertDialog {
                isDisplayed()
                message {
                    isDisplayed()
                    hasText("Name1 Won the game!")
                }
                positiveButton {
                    isDisplayed()
                    hasText(R.string.ok_btn_label)
                    click()
                }
            }
        }
    }

    @Test
    fun should_show_alert_dialog_when_reset_btn_is_clicked() {
        onScreen<GameScreen> {
            gameRecyclerView {
                for (i in 0 until 5) {
                    childAt<GameScreen.Item>(i) {
                        itemView {
                            click()
                        }
                    }
                }
            }
            resetBtn {
                isDisplayed()
                isEnabled()
                isClickable()
                hasText(R.string.reset)
                click()
            }

            alertDialog {
                isDisplayed()
                message {
                    isDisplayed()
                    hasText(R.string.reset_msg)
                }
                positiveButton {
                    isDisplayed()
                    hasText(R.string.reset)
                    click()
                }
            }
            gameRecyclerView {
                for (i in 0 until total_cells) {
                    childAt<GameScreen.Item>(i) {
                        itemView {
                            hasEmptyText()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun test_with_random_clicks_on_the_cells_after_game_is_finished() = runBlockingTest {
        onScreen<GameScreen> {
            gameRecyclerView {
                childAt<GameScreen.Item>(0) {
                    itemView {
                        click()
                    }
                }
                childAt<GameScreen.Item>(3) {
                    itemView {
                        click()
                    }
                }
                childAt<GameScreen.Item>(1) {
                    itemView {
                        click()
                    }
                }
                childAt<GameScreen.Item>(4) {
                    itemView {
                        click()
                    }
                }
                childAt<GameScreen.Item>(2) {
                    itemView {
                        click()
                    }
                }
                childAt<GameScreen.Item>(5) {
                    itemView {
                        click()
                        hasEmptyText()
                    }
                }
            }
            dispatcher.advanceUntilIdle()
            alertDialog {
                isDisplayed()
                message {
                    isDisplayed()
                    hasText("Name1 Won the game!")
                }
                positiveButton {
                    isDisplayed()
                    hasText(R.string.ok_btn_label)
                    click()
                }
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    companion object {
        const val player1Name = "Name1"
        const val player2Name = "Name2"
        const val player_1_name_key = "player1Name"
        const val player_2_name_key = "player2Name"
        const val total_cells = 9
    }
}