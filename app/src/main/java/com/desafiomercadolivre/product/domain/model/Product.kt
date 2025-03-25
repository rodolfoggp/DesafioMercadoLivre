package com.desafiomercadolivre.product.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val integerPrice: String,
    val fractionalPrice: String? = null,
    val originalPrice: String? = null,
    val imageUrl: String,
    val brand: String?,
    val installments: String?,
)
