package com.desafiomercadolivre.product.presentation.details

import com.desafiomercadolivre.architecture.presentation.StateViewModel
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState

class ProductDetailsViewModel : StateViewModel<ProductDetailsState>(ProductDetailsState()) {
    fun setProduct(product: Product) {
        changeState { it.copy(product = product) }
    }
}