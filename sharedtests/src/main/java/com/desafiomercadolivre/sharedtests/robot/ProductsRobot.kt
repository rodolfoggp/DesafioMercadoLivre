package com.desafiomercadolivre.sharedtests.robot

import com.desafiomercadolivre.product.data.service.ProductService
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.sharedtests.common.data.file.file

fun <T> productsService(func: ProductsRobot.() -> T) = robot(func)

class ProductsRobot {

    private val successQueryJson = file("searchQueryResponse.json").text()

    val expectedProducts = listOf(
        Product(
            "MLB3994232477", "Celular Moto G6 Play 32gb + Carregador E Nota Fiscal", 449.91
        ), Product(
            "MLB5299072326",
            "Moto G6 Play Dual Sim 32 Gb Índigo-escuro 3 Gb Ram - Bom (Recondicionado)",
            329.0
        )
    )

    fun returnsSuccessFor(query: String) = server {
        val encodedQuery = encode(query)
        setResponse(
            ProductService.SEARCH_QUERY_ENDPOINT + "?q=$encodedQuery",
            response(body = successQueryJson),
        )
    }

    private fun encode(query: String) = query.replace(" ", "%20")
}