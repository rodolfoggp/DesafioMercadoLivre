package com.desafiomercadolivre.sharedtests.robot

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.search.data.datasource.AccessTokenDataSource

fun <T> accessToken(func: AccessTokenRobot.() -> T) = robot(func)

class AccessTokenRobot {

    private val tokenDataSource: AccessTokenDataSource by inject()

    fun get() = tokenDataSource.get()
}