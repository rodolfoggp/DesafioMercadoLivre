package com.desafiomercadolivre.product.presentation.list

import androidx.lifecycle.viewModelScope
import com.desafiomercadolivre.architecture.presentation.ViewModel
import com.desafiomercadolivre.product.domain.usecase.SearchProductsUseCase
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError.NO_INTERNET
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError.UNKNOWN_ERROR
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.list.model.ProductsListState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.io.IOException

class ProductsListViewModel(
    val searchProductsUseCase: SearchProductsUseCase,
    val dispatcher: CoroutineDispatcher,
) : ViewModel<ProductsListState, ProductsListAction>(ProductsListState()) {

    fun searchProducts(query: String?) = viewModelScope.launch(dispatcher) {
        runCatching {
            changeState { ProductsListState(isLoading = true) }
            val products = searchProductsUseCase(query ?: throw IllegalArgumentException())
            changeState { ProductsListState(products = products) }
        }.onFailure(::handleError)
    }

    fun onSearchViewClicked() = sendAction { ShowSearchScreen }

    private fun handleError(throwable: Throwable) {
        val error = when (throwable) {
            is IOException -> NO_INTERNET
            else -> UNKNOWN_ERROR
        }
        changeState { ProductsListState(error = error) }
    }

    enum class ProductsListError {
        NO_INTERNET,
        UNKNOWN_ERROR
    }
}