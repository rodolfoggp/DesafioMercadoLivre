package com.desafiomercadolivre.rule

import android.app.Activity
import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.desafiomercadolivre.di.androidTestModules
import com.desafiomercadolivre.sharedtests.rule.KoinRule
import com.desafiomercadolivre.sharedtests.rule.MainDispatcherRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class ActivityTestRule<A : Activity>(activityClass: Class<A>) : TestRule {
    private val activityScenarioRule = ActivityScenarioRule(activityClass)

    override fun apply(base: Statement, description: Description): Statement {
        return RuleChain
            .outerRule(KoinRule(androidTestModules))
            .around(MainDispatcherRule())
            .around(InstantTaskExecutorRule())
            .around(activityScenarioRule)
            .apply(base, description)
    }
}