package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseItem(
    val id: String,
    val title: String,
    val price: Double,
    @SerialName("original_price") val originalPrice: Double? = null,
    val thumbnail: String,
    val attributes: List<SearchQueryResponseAttribute>,
    val installments: SearchQueryResponseInstallments?,
)