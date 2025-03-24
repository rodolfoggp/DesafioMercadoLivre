package com.desafiomercadolivre.product.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.desafiomercadolivre.databinding.ProductsListItemBinding
import com.desafiomercadolivre.product.domain.model.Product

class ProductsAdapter(
    val onClickAction: (String) -> Unit,
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
        with(holder.binding) {
            with(products[position]) {
                glide.load(imageUrl).into(productImage)

            }
        }
        /*val glide = Glide.with(holder.binding.root.context)
        with(holder.binding) {
            with(games[position]) {
                team1Name.text = team1Performance.team
                team2Name.text = team2Performance.team
                team1Score.text = team1Performance.score.toString()
                team2Score.text = team2Performance.score.toString()
                date.text = dateTime.weekDayAndDateString()
                time.text = dateTime.timeString()
                glide.load(team1Performance.badge).into(team1Badge)
                glide.load(team2Performance.badge).into(team2Badge)
            }
        }*/
    }

    override fun getItemCount() = products.size

    fun updateData(newGames: List<Product>) {
        products = newGames
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(val binding: ProductsListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) = onClickAction(products[layoutPosition].id)
    }
}