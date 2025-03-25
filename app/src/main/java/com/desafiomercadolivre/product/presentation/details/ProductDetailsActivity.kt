package com.desafiomercadolivre.product.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductDetailsBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.details.model.ProductDetailsState
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductDetailsBinding::inflate)
    private val viewModel by viewModel<ProductDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupViewModel()
        onStateChange(viewModel, ::handleState)
    }

    private fun setupViewModel() {
        val serializedProduct = intent.getStringExtra(PRODUCT)!!
        val product = Json.decodeFromString<Product>(serializedProduct)
        viewModel.setProduct(product)
    }

    private fun handleState(state: ProductDetailsState) = state.product?.let {
        with(binding) {
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

    companion object {
        const val PRODUCT = "product"
    }
}