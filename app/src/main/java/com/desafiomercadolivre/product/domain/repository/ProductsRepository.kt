package com.desafiomercadolivre.product.domain.repository

import com.desafiomercadolivre.product.domain.model.Product

interface ProductsRepository {
    suspend fun getByQuery(query: String): List<Product>
}