package com.desafiomercadolivre.product.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: String,
    val brand: String?,
    val hasFreeShipping: Boolean,
    val installments: Installments?,
)