package com.desafiomercadolivre.product.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductsListBinding
import com.desafiomercadolivre.product.presentation.model.ProductsListAction
import com.desafiomercadolivre.product.presentation.model.ProductsListAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.model.ProductsListState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsListActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductsListBinding::inflate)
    private val viewModel: ProductsListViewModel by viewModel()
    private val productsAdapter = ProductsAdapter(::onProductClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupLayout()
        initializeViewModel()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
    }

    private fun setupLayout() = with(binding) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
        }
    }

    private fun initializeViewModel() {
        val query = intent.getStringExtra(QUERY)!!
        viewModel.searchProducts(query)
    }

    private fun handleState(state: ProductsListState) = with(state) {
        productsAdapter.updateData(products)
    }

    private fun handleAction(action: ProductsListAction) {
        when (action) {
            ShowSearchScreen -> TODO()
        }
    }

    private fun onProductClicked(productId: String) {
        TODO("Not yet implemented")
    }

    companion object {
        const val QUERY = "query"
    }
}
