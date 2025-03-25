package com.desafiomercadolivre.product.presentation.list.model

import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError

data class ProductsListState(
    val isLoading: Boolean = false,
    val products: List<Product>? = null,
    val error: ProductsListError? = null,
)