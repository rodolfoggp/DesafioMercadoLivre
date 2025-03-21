package com.desafiomercadolivre.sharedtests.common.di

import com.desafiomercadolivre.sharedtests.robot.AccessTokenRobot
import com.desafiomercadolivre.sharedtests.robot.ProductsRobot
import com.desafiomercadolivre.sharedtests.robot.ServerRobot
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val robotsModule = module {
    singleOf(::ServerRobot)
    singleOf(::ProductsRobot)
    singleOf(::AccessTokenRobot)
}