package com.desafiomercadolivre.product.presentation.model

sealed class ProductsListAction {
     data object ShowSearchScreen : ProductsListAction()
}