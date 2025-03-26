package com.desafiomercadolivre.sharedtests.rule

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

class KoinRule(private val loadedModules: List<Module>) : TestWatcher() {
    override fun starting(description: Description) {
        GlobalContext.getOrNull() ?: startKoin(koinApplication())
        loadKoinModules(loadedModules)
        super.starting(description)
    }

    override fun finished(description: Description) {
        stopKoin()
        super.finished(description)
    }
}