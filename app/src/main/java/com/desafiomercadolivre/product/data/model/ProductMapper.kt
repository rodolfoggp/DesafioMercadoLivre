package com.desafiomercadolivre.product.data.model

import com.desafiomercadolivre.product.domain.model.Product

fun SearchQueryResponseItem.toProduct() = Product(id, title, price)
