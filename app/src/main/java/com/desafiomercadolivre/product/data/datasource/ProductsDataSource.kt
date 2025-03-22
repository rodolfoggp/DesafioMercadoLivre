package com.desafiomercadolivre.product.data.datasource

import com.desafiomercadolivre.product.domain.model.Product

interface ProductsDataSource {
    suspend fun getByQuery(query: String, accessToken: String): List<Product>
}