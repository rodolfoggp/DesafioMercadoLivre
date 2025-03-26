package com.desafiomercadolivre.product.data.model

import com.desafiomercadolivre.product.domain.model.Installments
import com.desafiomercadolivre.product.domain.model.Product

class ProductMapper {
    fun map(response: SearchQueryResponseItem) = with(response) {
        Product(
            id = id,
            title = title,
            price = price,
            originalPrice = originalPrice,
            imageUrl = thumbnail,
            brand = attributes.firstOrNull { id == "BRAND" }?.valueName,
            hasFreeShipping = shipping?.isFree ?: false,
            installments = installments?.let {
                Installments(
                    quantity = it.quantity,
                    amount = it.amount,
                    additionalInterest = it.metadata.additionalBankInterest,
                )
            }
        )
    }
}
