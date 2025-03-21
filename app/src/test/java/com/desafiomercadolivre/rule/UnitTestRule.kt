package com.desafiomercadolivre.rule

import com.desafiomercadolivre.di.unitTestModules
import com.desafiomercadolivre.sharedtests.rule.KoinRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class UnitTestRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return RuleChain
            .outerRule(KoinRule(*unitTestModules))
            //.around(MainDispatcherRule())
            //.around(InstantTaskExecutorRule())
            .apply(base, description)
    }
}