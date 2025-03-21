package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponse(val results: List<SearchQueryResponseItem>)