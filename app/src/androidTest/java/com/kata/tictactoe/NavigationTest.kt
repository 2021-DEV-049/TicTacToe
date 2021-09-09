package com.kata.tictactoe

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kata.tictactoe.ui.fragments.EntryFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @Test
    fun should_navigate_to_expected_destination_with_bundle() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val entryScenario = launchFragmentInContainer<EntryFragment>(
            themeResId = R.style.TicTacToe
        )

        entryScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(ViewMatchers.withId(R.id.et_player1)).perform(typeText("Dinesh"))
        onView(ViewMatchers.withId(R.id.et_player2)).perform(
            typeText("Logan"),
            closeSoftKeyboard()
        )
        onView(ViewMatchers.withId(R.id.play_btn)).perform(ViewActions.click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.gameFragment)
        val currentDestinationArgs = navController.currentBackStackEntry?.arguments
        assertThat(currentDestinationArgs?.getString("player1Name")).isEqualTo("Dinesh")
        assertThat(currentDestinationArgs?.getString("player2Name")).isEqualTo("Logan")
    }
}