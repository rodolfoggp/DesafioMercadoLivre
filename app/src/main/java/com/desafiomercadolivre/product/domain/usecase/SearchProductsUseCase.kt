package com.desafiomercadolivre.product.domain.usecase

import com.desafiomercadolivre.product.domain.model.Product

fun interface SearchProductsUseCase: suspend (String) -> List<Product>
