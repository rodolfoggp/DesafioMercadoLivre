package com.desafiomercadolivre.product.data.model

import com.desafiomercadolivre.R
import com.desafiomercadolivre.common.resource.ResourceProvider
import com.desafiomercadolivre.product.domain.model.Product
import java.util.Locale

class ProductMapper(private val resourceProvider: ResourceProvider) {
    fun map(response: SearchQueryResponseItem) = with(response) {
        val (priceInteger, priceFractional) = priceAsIntegerAndFractional(price)
        Product(
            id = id,
            title = title,
            price = price,
            integerPrice = priceInteger.withCurrency(),
            fractionalPrice = priceFractional,
            originalPrice = originalPrice?.asPriceString()?.withCurrency(),
            imageUrl = thumbnail.withHttps(),
            brand = getBrand(),
            installments = installments?.getInstallmentsText(),
        )
    }

    private fun Double.asPriceString() =
        String.format(Locale("pt", "BR"), "%.2f", this)

    private fun String.withCurrency() =
        resourceProvider.getString(R.string.price_in_brl, this)

    private fun priceAsIntegerAndFractional(price: Double): Pair<String, String?> {
        val parts = price.asPriceString().split(",")

        val priceInteger = parts[0]

        val secondPart = parts[1]
        val priceFractional = if (secondPart == "00") null else secondPart

        return priceInteger to priceFractional
    }

    private fun SearchQueryResponseItem.getBrand(): String? =
        attributes.firstOrNull { id == "BRAND" }?.valueName

    private fun SearchQueryInstallments.getInstallmentsText(): String {
        val installments = resourceProvider.getString(R.string.installments, quantity, amount.asPriceString())
        val interests = if (!metadata.additionalBankInterest)  {
            resourceProvider.getString(R.string.no_interest)
        } else {
            ""
        }
        return "$installments $interests"
    }

    private fun String.withHttps() =
        if (startsWith("http")) {
            "https" + dropWhile { it != ':' }
        } else {
            this
        }
}
