package com.desafiomercadolivre.product.presentation.details.model

sealed class ProductDetailsAction {
    data object ShowSearchScreen : ProductDetailsAction()
}