package com.desafiomercadolivre.product.di

import com.desafiomercadolivre.common.data.service.MercadoLivreApi
import com.desafiomercadolivre.product.data.datasource.ProductsServiceDataSource
import com.desafiomercadolivre.product.data.service.ProductService
import com.desafiomercadolivre.product.domain.datasource.ProductsDataSource
import com.desafiomercadolivre.search.data.datasource.AccessTokenDataSource
import com.desafiomercadolivre.search.data.datasource.HardcodedAccessTokenDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val productModule = module {
    singleOf<ProductService>(MercadoLivreApi::create)

    factoryOf(::ProductsServiceDataSource) bind ProductsDataSource::class
    factoryOf(::HardcodedAccessTokenDataSource) bind AccessTokenDataSource::class
}