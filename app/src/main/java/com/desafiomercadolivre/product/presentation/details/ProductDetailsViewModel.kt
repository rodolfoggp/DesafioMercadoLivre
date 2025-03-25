package com.desafiomercadolivre.product.presentation.details

import com.desafiomercadolivre.architecture.presentation.ViewModel
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState

class ProductDetailsViewModel :
    ViewModel<ProductDetailsState, ProductDetailsAction>(ProductDetailsState()) {

    fun setProduct(product: Product) = changeState { it.copy(product = product) }

    fun onSearchViewClicked() = sendAction { ShowSearchScreen }
}