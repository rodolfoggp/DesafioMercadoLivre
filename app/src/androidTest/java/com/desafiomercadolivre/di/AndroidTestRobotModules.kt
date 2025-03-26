package com.desafiomercadolivre.di

import com.desafiomercadolivre.robot.HomeRobot
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidTestRobotModules = module {
    singleOf(::HomeRobot)
}