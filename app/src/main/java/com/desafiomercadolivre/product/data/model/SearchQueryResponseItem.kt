package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseItem(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
)