package com.desafiomercadolivre.product.presentation

import com.desafiomercadolivre.R
import com.desafiomercadolivre.common.resource.ResourceProvider
import com.desafiomercadolivre.product.domain.model.Installments
import java.util.Locale

interface ProductsFormatter {
    val resourceProvider: ResourceProvider

    fun Double.asPriceString() =
        String.format(Locale("pt", "BR"), "%.2f", this)

    fun String.withCurrency() =
        resourceProvider.getString(R.string.price_in_brl, this)

    fun priceAsIntegerAndFractional(price: Double): Pair<String, String?> {
        val parts = price.asPriceString().split(",")

        val priceInteger = parts[0]

        val secondPart = parts[1]
        val priceFractional = if (secondPart == "00") null else secondPart

        return priceInteger to priceFractional
    }

    fun getInstallmentsText(installments: Installments): String = with(installments) {
        val installmentsText = resourceProvider.getString(R.string.installments, quantity, amount.asPriceString())
        val interestsText = if (!additionalInterest) {
            resourceProvider.getString(R.string.no_interest)
        } else {
            ""
        }
        return "$installmentsText $interestsText"
    }

    fun String.withHttps() =
        if (startsWith("http")) {
            "https" + dropWhile { it != ':' }
        } else {
            this
        }
}