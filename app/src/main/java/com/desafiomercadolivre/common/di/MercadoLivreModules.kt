package com.desafiomercadolivre.common.di

import com.desafiomercadolivre.product.di.productModule
import com.desafiomercadolivre.search.di.searchModule

val mercadoLivreModules = listOf(
    searchModule,
    productModule,
)