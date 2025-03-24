package com.desafiomercadolivre.product.data.datasource

import com.desafiomercadolivre.common.data.model.BearerAuthorization
import com.desafiomercadolivre.product.data.model.ProductMapper
import com.desafiomercadolivre.product.data.service.ProductsService
import com.desafiomercadolivre.product.domain.model.Product

class ProductsServiceDataSource(
    private val service: ProductsService,
    private val mapper: ProductMapper,
): ProductsDataSource {
    override suspend fun getByQuery(query: String, accessToken: String): List<Product> {
        val response = service.search(query, BearerAuthorization(accessToken))
        return response.results.map { mapper.map(it) }
    }
}
