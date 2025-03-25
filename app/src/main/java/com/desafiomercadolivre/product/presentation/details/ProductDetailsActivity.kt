package com.desafiomercadolivre.product.presentation.details

import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductDetailsBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState
import com.desafiomercadolivre.search.presentation.SearchActivity
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductDetailsBinding::inflate)
    private val viewModel by viewModel<ProductDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupLayout()
        setupViewModel()
        onStateChange(viewModel, ::handleState)
        onAction(viewModel, ::handleAction)
    }

    private fun setupLayout() = with(binding) {
        toolbar.searchView.setOnClickListener { viewModel.onSearchViewClicked() }
    }

    private fun setupViewModel() {
        val serializedProduct = intent.getStringExtra(PRODUCT)
        val product = serializedProduct?.let { Json.decodeFromString<Product>(it) }
        viewModel.setProduct(product)
    }

    private fun handleState(state: ProductDetailsState) = with(state) {
        error?.let {
            //handleError
        }
        product?.let { showProduct(it) }
    }

    private fun showProduct(product: Product) = with(binding) {
        product.let {
            Glide.with(this@ProductDetailsActivity)
                .load(it.imageUrl)
                .into(productImage)
            brand.apply {
                text = it.brand
                isVisible = !text.isNullOrBlank()
            }
            title.text = it.title
            originalPrice.apply {
                text = it.originalPrice
                isVisible = !text.isNullOrBlank()
            }
            priceInteger.text = it.integerPrice
            priceFractional.text = it.fractionalPrice
            installmentsConditions.text = it.installments
            freeShippingTag.isVisible = it.hasFreeShipping
        }
    }

    private fun handleAction(action: ProductDetailsAction) {
        when (action) {
            ShowSearchScreen -> showSearchScreen()
        }
    }

    private fun showSearchScreen() {
        startActivity(SearchActivity::class.java) {
            addFlags(FLAG_ACTIVITY_NO_HISTORY)
        }
    }

    companion object {
        const val PRODUCT = "product"
    }
}