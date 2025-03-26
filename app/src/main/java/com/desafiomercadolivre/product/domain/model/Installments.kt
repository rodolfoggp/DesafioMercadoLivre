package com.desafiomercadolivre.product.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Installments(
    val quantity: Int,
    val amount: Double,
    val additionalInterest: Boolean,
)