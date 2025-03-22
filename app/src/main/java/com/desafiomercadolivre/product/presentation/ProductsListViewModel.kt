package com.desafiomercadolivre.product.presentation

import androidx.lifecycle.viewModelScope
import com.desafiomercadolivre.architecture.presentation.ViewModel
import com.desafiomercadolivre.product.domain.usecase.SearchProductsUseCase
import com.desafiomercadolivre.product.presentation.model.ProductsListAction
import com.desafiomercadolivre.product.presentation.model.ProductsListAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.model.ProductsListState
import kotlinx.coroutines.launch

class ProductsListViewModel(
    val searchProductsUseCase: SearchProductsUseCase,
) : ViewModel<ProductsListState, ProductsListAction>(ProductsListState()) {

    fun searchProducts(query: String) = viewModelScope.launch {
        val products = searchProductsUseCase(query)
        changeState { it.copy(products = products, isLoading = false) }
    }

    fun onSearchEditTextClicked() = sendAction { ShowSearchScreen }
}

