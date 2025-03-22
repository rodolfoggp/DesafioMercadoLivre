package com.desafiomercadolivre.sharedtests.common.di

import com.desafiomercadolivre.product.data.service.ProductsService
import com.desafiomercadolivre.sharedtests.common.data.service.TestApi
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val sharedTestsModule = module {
    includes(robotsModule)
    factoryOf<ProductsService>(TestApi::create)
}