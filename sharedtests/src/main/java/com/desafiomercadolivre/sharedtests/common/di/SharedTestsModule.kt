package com.desafiomercadolivre.sharedtests.common.di

import com.desafiomercadolivre.common.resource.ResourceProvider
import com.desafiomercadolivre.product.data.service.ProductsService
import com.desafiomercadolivre.sharedtests.common.data.service.TestApi
import com.desafiomercadolivre.sharedtests.common.resource.FakeResourceProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val sharedTestsModule = module {
    includes(robotsModule)

    single<CoroutineDispatcher> { UnconfinedTestDispatcher() }

    factoryOf(::FakeResourceProvider) bind ResourceProvider::class

    factoryOf<ProductsService>(TestApi::create)
}

