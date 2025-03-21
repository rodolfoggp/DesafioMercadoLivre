package com.desafiomercadolivre.search.data.datasource

interface AccessTokenDataSource {
    fun get(): String
}