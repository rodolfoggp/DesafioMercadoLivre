package com.desafiomercadolivre.product.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.desafiomercadolivre.architecture.extensions.onStateChange
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.architecture.presentation.StateViewModel
import com.desafiomercadolivre.databinding.ActivityProductDetailsBinding
import com.desafiomercadolivre.product.domain.model.Product
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
        Glide.with(this@ProductDetailsActivity)
            .load(it.imageUrl)
            .into(binding.productImage)
    }

    companion object {
        const val PRODUCT = "product"
    }
}

class ProductDetailsViewModel : StateViewModel<ProductDetailsState>(ProductDetailsState()) {
    fun setProduct(product: Product) {
        changeState { it.copy(product = product) }
    }
}

data class ProductDetailsState(
    val product: Product? = null
)
