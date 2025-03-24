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
    val installments: SearchQueryInstallments?,
)

@Serializable
data class SearchQueryResponseAttribute(
    val id: String,
    @SerialName("value_name") val valueName: String? = null,
)

@Serializable
data class SearchQueryInstallments(
    val quantity: Int,
    val amount: Double,
    val metadata: SearchQueryInstallmentsMetadata,
)

@Serializable
data class SearchQueryInstallmentsMetadata(
    @SerialName("additional_bank_interest") val additionalBankInterest: Boolean
)