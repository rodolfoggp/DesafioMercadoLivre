package com.desafiomercadolivre.product.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafiomercadolivre.databinding.ProductsListItemBinding
import com.desafiomercadolivre.product.domain.model.Product

class ProductsAdapter(
    val onClickAction: (Product) -> Unit,
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

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
            glide.load(product.imageUrl)
                .into(productImage)
            brand.apply {
                text = product.brand
                isVisible = !text.isNullOrBlank()
            }
            title.text = product.title
            originalPrice.apply {
                text = product.originalPrice
                isVisible = !text.isNullOrBlank()
            }
            priceInteger.text = product.integerPrice
            priceFractional.text = product.fractionalPrice
            installmentsConditions.text = product.installments
            freeShippingTag.isVisible = product.hasFreeShipping
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