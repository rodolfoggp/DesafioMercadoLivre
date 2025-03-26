package com.desafiomercadolivre.sharedtests.robot

import com.desafiomercadolivre.product.data.service.ProductsService
import com.desafiomercadolivre.product.domain.model.Installments
import com.desafiomercadolivre.product.domain.model.Product
import com.desafiomercadolivre.sharedtests.common.data.file.file

fun <T> products(func: ProductsRobot.() -> T) = robot(func)

class ProductsRobot {

    private val successQueryJson = file("searchQueryResponse.json").text()

    val expectedProducts = listOf(
        Product(
            "MLB3994232477",
            "Celular Moto G6 Play 32gb + Carregador E Nota Fiscal",
            449.91,
            499.9,
            "http://http2.mlstatic.com/D_921718-MLB82606397878_032025-O.jpg",
            null,
            true,
            Installments(8, 56.24, false),
        ), Product(
            "MLB5299072326",
            "Moto G6 Play Dual Sim 32 Gb Índigo-escuro 3 Gb Ram - Bom (Recondicionado)",
            329.0,
            null,
            "http://http2.mlstatic.com/D_971725-MLA31349066366_072019-I.jpg",
            null,
            true,
            Installments(6, 54.83, false),
        )
    )

    fun searchEndpointReturnsSuccessFor(query: String) = server {
        val encodedQuery = encode(query)
        setResponse(
            ProductsService.SEARCH_ENDPOINT + "?q=$encodedQuery",
            response(body = successQueryJson),
        )
    }

    private fun encode(query: String) = query.replace(" ", "%20")
}