package com.desafiomercadolivre.product.presentation.list.model

import com.desafiomercadolivre.product.domain.model.Product

data class ProductsListState(
    val isLoading: Boolean = true,
    val products: List<Product> = emptyList(),
)