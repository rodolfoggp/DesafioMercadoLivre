package com.desafiomercadolivre.sharedtests.common.di

import com.desafiomercadolivre.product.data.service.ProductsService
import com.desafiomercadolivre.sharedtests.common.data.service.TestApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val sharedTestsModule = module {
    includes(robotsModule)
    single<CoroutineDispatcher> { UnconfinedTestDispatcher() }
    factoryOf<ProductsService>(TestApi::create)
}

