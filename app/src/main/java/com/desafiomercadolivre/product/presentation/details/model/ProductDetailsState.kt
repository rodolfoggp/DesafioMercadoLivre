package com.desafiomercadolivre.product.presentation.details.model

import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.ProductDetailsViewModel.ProductDetailsError

data class ProductDetailsState(
    val product: Product? = null,
    val error: ProductDetailsError? = null,
)