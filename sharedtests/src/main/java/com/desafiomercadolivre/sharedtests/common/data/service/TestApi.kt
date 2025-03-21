package com.desafiomercadolivre.sharedtests.common.data.service

import com.desafiomercadolivre.common.data.service.ServiceFactory
import com.desafiomercadolivre.sharedtests.robot.server

object TestApi: ServiceFactory() {
    override val baseUrl: String
        get() = server { baseUrl }
}