package com.desafiomercadolivre.product.data.datasource

import com.desafiomercadolivre.common.data.model.BearerAuthorization
import com.desafiomercadolivre.product.data.model.toProduct
import com.desafiomercadolivre.product.data.service.ProductService
import com.desafiomercadolivre.product.domain.datasource.ProductsDataSource
import com.desafiomercadolivre.product.domain.model.Product

class ProductsServiceDataSource(
    private val service: ProductService,
): ProductsDataSource {
    override suspend fun getByQuery(query: String, accessToken: String): List<Product> {
        val response = service.search(query, BearerAuthorization(accessToken))
        return response.results.map { it.toProduct() }
    }
}
