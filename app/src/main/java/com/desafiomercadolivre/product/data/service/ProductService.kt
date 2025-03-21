package com.desafiomercadolivre.product.data.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET(SEARCH_QUERY_ENDPOINT)
    suspend fun search(@Query("q") query: String)

    companion object {
        const val SEARCH_QUERY_ENDPOINT = "/sites/MLB/search"
    }
}