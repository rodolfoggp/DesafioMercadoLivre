package com.desafiomercadolivre.product

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityProductsListBinding

class ProductsListActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductsListBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
    }

    companion object {
        const val QUERY = "query"
    }
}