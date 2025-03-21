package com.desafiomercadolivre.common.data.model

class BearerAuthorization(private val accessToken: String) {
    override fun toString() = "Bearer $accessToken"
}