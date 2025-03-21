package com.desafiomercadolivre.rule

import com.desafiomercadolivre.di.androidTestModules
import com.desafiomercadolivre.sharedtests.rule.KoinRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AndroidTestRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return RuleChain
            .outerRule(KoinRule(*androidTestModules.toTypedArray()))
            //.around(MainDispatcherRule())
            //.around(InstantTaskExecutorRule())
            .apply(base, description)
    }
}