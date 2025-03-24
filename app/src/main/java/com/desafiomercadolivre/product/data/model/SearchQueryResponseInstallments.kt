package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseInstallments(
    val quantity: Int,
    val amount: Double,
    val metadata: SearchQueryResponseInstallmentsMetadata,
)