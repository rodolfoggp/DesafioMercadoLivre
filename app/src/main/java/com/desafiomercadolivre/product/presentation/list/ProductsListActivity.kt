package com.desafiomercadolivre.product.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductsListBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.ProductDetailsActivity
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.list.model.ProductsListState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun initializeViewModel() {
        val query = intent.getStringExtra(QUERY)!!
        viewModel.searchProducts(query)
    }

    private fun handleState(state: ProductsListState) = with(state) {
        binding.loading.isVisible = isLoading
        binding.recyclerView.isVisible = !isLoading
        productsAdapter.updateData(products)
    }

    private fun handleAction(action: ProductsListAction) {
        when (action) {
            ShowSearchScreen -> TODO()
        }
    }

    private fun onProductClicked(product: Product) {
        val serializedProduct = Json.encodeToString(product)
        startActivity(ProductDetailsActivity::class.java) {
            putExtra(ProductDetailsActivity.PRODUCT, serializedProduct)
        }
    }

    companion object {
        const val QUERY = "query"
    }
}
