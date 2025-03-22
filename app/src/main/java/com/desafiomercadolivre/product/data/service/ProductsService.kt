package com.desafiomercadolivre.product.data.service

import com.desafiomercadolivre.common.data.model.BearerAuthorization
import com.desafiomercadolivre.product.data.model.SearchQueryResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductsService {

    @GET(SEARCH_ENDPOINT)
    suspend fun search(
        @Query("q") query: String,
        @Header("Authorization") authorizationHeader: BearerAuthorization,
    ): SearchQueryResponse

    companion object {
        const val SEARCH_ENDPOINT = "/sites/MLB/search"
    }
}