package com.desafiomercadolivre.sharedtests.rule

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class KoinRule(private vararg val modulesArgs: Module) : TestWatcher() {
    override fun starting(description: Description) {
        GlobalContext.startKoin { modules(*modulesArgs) }
        super.starting(description)
    }

    override fun finished(description: Description) {
        stopKoin()
        super.finished(description)
    }
}