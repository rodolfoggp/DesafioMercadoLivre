package com.desafiomercadolivre.product.presentation.model

import com.desafiomercadolivre.product.domain.model.Product

data class ProductsListState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
)