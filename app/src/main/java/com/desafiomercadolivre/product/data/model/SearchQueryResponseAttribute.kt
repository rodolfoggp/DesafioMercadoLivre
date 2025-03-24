package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseAttribute(
    val id: String,
    @SerialName("value_name") val valueName: String? = null,
)