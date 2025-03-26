package com.desafiomercadolivre.di

import androidx.test.platform.app.InstrumentationRegistry
import com.desafiomercadolivre.sharedtests.common.di.sharedTestsModule
import org.koin.dsl.module

val androidTestModules = sharedTestsModule + module {
    includes(androidTestRobotModules)
    factory { InstrumentationRegistry.getInstrumentation().context }
}