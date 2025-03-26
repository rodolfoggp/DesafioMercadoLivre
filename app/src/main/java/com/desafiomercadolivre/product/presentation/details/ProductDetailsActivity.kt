package com.desafiomercadolivre.product.presentation.details

import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.desafiomercadolivre.R
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.common.resource.ResourceProvider
import com.desafiomercadolivre.databinding.ActivityProductDetailsBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.ProductsFormatter
import com.desafiomercadolivre.product.presentation.details.ProductDetailsViewModel.ProductDetailsError
import com.desafiomercadolivre.product.presentation.details.ProductDetailsViewModel.ProductDetailsError.NULL_PRODUCT
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsAction.ShowSearchScreen
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState
import com.desafiomercadolivre.search.presentation.SearchActivity
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity(), ProductsFormatter {

    private val binding by viewBinding(ActivityProductDetailsBinding::inflate)
    private val viewModel by viewModel<ProductDetailsViewModel>()
    private val glide by lazy { Glide.with(this) }
    override val resourceProvider: ResourceProvider by inject()

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
        binding.errorView.root.isVisible = error?.showError() != null
        binding.content.isVisible = product?.showProduct() != null
    }

    private fun ProductDetailsError.showError() {
        val errorMessage: String
        val icon: Int
        when (this) {
            NULL_PRODUCT -> {
                errorMessage = getString(R.string.something_bad_happened_error)
                icon = R.drawable.ic_warning
            }
        }
        with(binding.errorView) {
            glide.load(icon).into(errorIcon)
            errorText.text = errorMessage
        }
    }

    private fun Product.showProduct() = with(binding) {
        glide.load(imageUrl.withHttps()).into(productImage)
        brandTextView.apply {
            text = brand
            isVisible = !text.isNullOrBlank()
        }
        titleTextView.text = title
        originalPriceTextView.apply {
            text = originalPrice?.asPriceString()?.withCurrency()
            isVisible = !text.isNullOrBlank()
        }
        val (priceInteger, priceFractional) = priceAsIntegerAndFractional(price)
        priceIntegerTextView.text = priceInteger
        priceFractionalTextView.text = priceFractional
        installmentsConditionsTextView.text = installments?.let { getInstallmentsText(it) }
        freeShippingTagTextView.isVisible = hasFreeShipping
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