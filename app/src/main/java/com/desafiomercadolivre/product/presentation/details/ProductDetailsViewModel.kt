package com.desafiomercadolivre.product.presentation.details

import com.desafiomercadolivre.architecture.presentation.ViewModel
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.ProductDetailsViewModel.ProductDetailsError.NULL_PRODUCT
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState

class ProductDetailsViewModel :
    ViewModel<ProductDetailsState, ProductDetailsAction>(ProductDetailsState()) {

    fun setProduct(product: Product?) {
        product?.let {
            changeState { ProductDetailsState(product = product) }
        } ?: run {
            changeState { ProductDetailsState(error = NULL_PRODUCT) }
        }
    }

    fun onSearchViewClicked() = sendAction { ShowSearchScreen }

    enum class ProductDetailsError {
        NULL_PRODUCT
    }
}