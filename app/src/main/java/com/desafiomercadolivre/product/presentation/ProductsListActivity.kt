package com.desafiomercadolivre.product.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.common.data.model.BearerAuthorization
import com.desafiomercadolivre.databinding.ActivityProductsListBinding
import com.desafiomercadolivre.product.data.service.ProductService
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductsListActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityProductsListBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
    }


    private val service: ProductService by inject()
    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val authorizationHeader = BearerAuthorization("APP_USR-7092-031917-a89f85b7cf80039d243a9397312351a7-2341341668")
            val response = service.searchQuery("Motorola G6", authorizationHeader)
            println(response)
        }
    }

    companion object {
        const val QUERY = "query"
    }
}