package com.desafiomercadolivre.search.data.datasource

class HardcodedAccessTokenDataSource : AccessTokenDataSource {
    /**
     * Hardcoded access token for the sole purpose of using it in this exercise.
     * In a real application, the access token should not be stored in this manner.
     */
    override fun get() = "APP_USR-7092-031917-a89f85b7cf80039d243a9397312351a7-2341341668"
}