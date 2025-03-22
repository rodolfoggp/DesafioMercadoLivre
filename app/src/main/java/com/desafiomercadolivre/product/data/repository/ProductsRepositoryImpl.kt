package com.desafiomercadolivre.product.data.repository

import com.desafiomercadolivre.product.data.datasource.ProductsDataSource
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.product.domain.repository.ProductsRepository
import com.desafiomercadolivre.search.data.datasource.AccessTokenDataSource

class ProductsRepositoryImpl(
    private val productsDataSource: ProductsDataSource,
    private val accessTokenDataSource: AccessTokenDataSource,
) : ProductsRepository {
    override suspend fun getByQuery(query: String): List<Product> =
        productsDataSource.getByQuery(query, accessTokenDataSource.get())
}