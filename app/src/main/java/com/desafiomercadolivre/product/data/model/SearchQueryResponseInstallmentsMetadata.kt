package com.desafiomercadolivre.product.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponseInstallmentsMetadata(
    @SerialName("additional_bank_interest") val additionalBankInterest: Boolean
)