package com.desafiomercadolivre.home.presentation

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.desafiomercadolivre.robot.home
import com.desafiomercadolivre.rule.ActivityTestRule
import com.desafiomercadolivre.search.presentation.SearchActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    val rule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun clickOnSearchView_ShouldNavigateToSearchActivity() {
        // When
        home { clickOnSearchView() }

        // Then
        Intents.intended(hasComponent(SearchActivity::class.java.name))
    }
}