package com.desafiomercadolivre.product.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafiomercadolivre.common.resource.ResourceProvider
import com.desafiomercadolivre.databinding.ProductsListItemBinding
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.presentation.ProductsFormatter

class ProductsAdapter(
    val onClickAction: (Product) -> Unit,
    override val resourceProvider: ResourceProvider,
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>(), ProductsFormatter {

    private var products: List<Product> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val context = holder.binding.root.context
        val glide = Glide.with(context)
        val product = products[position]
        with(holder.binding) {
            glide.load(product.imageUrl.withHttps())
                .into(productImage)
            brandTextView.apply {
                text = product.brand
                isVisible = !text.isNullOrBlank()
            }
            titleTextView.text = product.title
            originalPriceTextView.apply {
                text = product.originalPrice?.asPriceString()?.withCurrency()
                isVisible = !text.isNullOrBlank()
            }
            val (priceInteger, priceFractional) = priceAsIntegerAndFractional(product.price)
            priceIntegerTextView.text = priceInteger.withCurrency()
            priceFractionalTextView.text = priceFractional
            installmentsConditionsTextView.text =
                product.installments?.let { getInstallmentsText(it) }
            freeShippingTagTextView.isVisible = product.hasFreeShipping
        }
    }

    override fun getItemCount() = products.size

    fun updateData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(val binding: ProductsListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) = onClickAction(products[layoutPosition])
    }
}