package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseShipping(
    @SerialName("free_shipping") val isFree: Boolean
)