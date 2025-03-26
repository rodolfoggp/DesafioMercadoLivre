package com.desafiomercadolivre.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.desafiomercadolivre.R
import com.desafiomercadolivre.sharedtests.robot.robot

fun <T> home(func: HomeRobot.() -> T) = robot(func)

class HomeRobot {
    fun clickOnSearchView() {
        onView(withId(R.id.searchView)).perform(click())
    }
}