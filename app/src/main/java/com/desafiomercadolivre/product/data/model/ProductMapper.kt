package com.desafiomercadolivre.product.data.model

import com.desafiomercadolivre.product.domain.model.Product

fun SearchQueryResponseItem.toProduct() =
    Product(
        id = id,
        title = title,
        price = price,
        imageUrl = thumbnail.withHttps()
    )

private fun String.withHttps() =
    if (startsWith("http")) {
        "https" + dropWhile { it != ':' }
    } else {
        this
    }

