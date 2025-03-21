package com.desafiomercadolivre.common.data.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class ServiceFactory {
    abstract val baseUrl: String

    private val json = Json { ignoreUnknownKeys = true }
    private val contentType: MediaType = "application/json".toMediaType()
    private val httpClient: OkHttpClient = OkHttpClient.Builder().build()

    val retrofit: Retrofit get() = buildRetrofit()

    @OptIn(ExperimentalSerializationApi::class)
    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)
        .build()

    inline fun <reified S> create(): S = retrofit.create(S::class.java)
}