package com.desafiomercadolivre.product.di

import com.desafiomercadolivre.common.data.service.MercadoLivreApi
import com.desafiomercadolivre.product.data.service.ProductService
import org.koin.dsl.module

val productModule = module {
    single { MercadoLivreApi.create<ProductService>() }
}