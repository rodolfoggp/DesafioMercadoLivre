package com.desafiomercadolivre.product.presentation.list

import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.desafiomercadolivre.R
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductsListBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.ProductDetailsActivity
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError.NO_INTERNET
import com.desafiomercadolivre.product.presentation.list.ProductsListViewModel.ProductsListError.UNKNOWN_ERROR
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction
import com.desafiomercadolivre.product.presentation.list.model.ProductsListAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.list.model.ProductsListState
import com.desafiomercadolivre.search.presentation.SearchActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsListActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductsListBinding::inflate)
    private val viewModel: ProductsListViewModel by viewModel()
    private val productsAdapter = ProductsAdapter(::onProductClicked)
    private val glide by lazy { Glide.with(this) }

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
        toolbar.searchView.setOnClickListener { viewModel.onSearchViewClicked() }
    }

    private fun initializeViewModel() {
        val query = intent.getStringExtra(QUERY)
        viewModel.searchProducts(query)
    }

    private fun handleState(state: ProductsListState) = with(state) {
        with(binding) {
            loading.isVisible = isLoading
            errorView.root.isVisible = error?.handleError() != null
            recyclerView.isVisible = products?.let { productsAdapter.updateData(it) } != null
        }
    }

    private fun ProductsListError.handleError() {
        val errorMessage: String
        val icon: Int
        when (this) {
            UNKNOWN_ERROR -> {
                icon = R.drawable.ic_warning
                errorMessage = getString(R.string.something_bad_happened_error)
            }

            NO_INTERNET -> {
                icon = R.drawable.ic_no_internet
                errorMessage = getString(R.string.no_internet_error)
            }
        }
        with(binding.errorView) {
            glide.load(icon).into(errorIcon)
            errorText.text = errorMessage
        }
    }

    private fun handleAction(action: ProductsListAction) {
        when (action) {
            ShowSearchScreen -> showSearchScreen()
        }
    }

    private fun showSearchScreen() {
        startActivity(SearchActivity::class.java) {
            addFlags(FLAG_ACTIVITY_NO_HISTORY)
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
