package com.desafiomercadolivre.product.presentation.list.model

sealed class ProductsListAction {
     data object ShowSearchScreen : ProductsListAction()
}