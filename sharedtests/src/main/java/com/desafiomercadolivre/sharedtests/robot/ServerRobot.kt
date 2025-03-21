package com.desafiomercadolivre.sharedtests.robot

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.TimeUnit

fun <T> server(func: ServerRobot.() -> T) = robot(func)

class ServerRobot {
    private val server = MockWebServer().apply {
        start()
        dispatcher = createDispatcher()
    }

    val baseUrl = server.url("").toString()

    val errorResponse = response(400)
    val successResponse = response(200)
    private val responseNotFound = response(501)

    private val responseForPath = HashMap<String, MockResponse>()

    private fun createDispatcher() = dispatcher { request ->
        request.log()
        responseForPath[request.path] ?: responseNotFound
    }

    private fun dispatcher(handler: Dispatcher.(RecordedRequest) -> MockResponse): Dispatcher =
        object : Dispatcher() {
            override fun dispatch(request: RecordedRequest) = handler(request)
        }

    private fun RecordedRequest.log() = println("Request made to path: $path")

    fun setResponse(path: String, response: MockResponse) {
        responseForPath[path.withSlash()] = response
    }

    private fun String.withSlash() = if (isRelative()) "/$this" else this

    private fun String.isRelative() = !startsWith("/")

    fun requests(): List<RecordedRequest> {
        val requestsMade = arrayListOf<RecordedRequest>()
        while (server.requestCount > 0) {
            requestsMade.add(server.takeRequest())
        }
        return requestsMade
    }

    fun response(code: Int = 200, body: String = "") = MockResponse().apply {
        setBody(body)
        setResponseCode(code)
    }

    fun takeRequest(): RecordedRequest = server.takeRequest()
    fun takeRequest(timeout: Long, unit: TimeUnit = TimeUnit.SECONDS) = server.takeRequest(timeout, unit)

    fun requestsHaveBeenMade() = server.requestCount > 0

    fun RecordedRequest.hasBodyEqualTo(expectedBody: String): Boolean {
        val actualBody = String(body.readByteArray())
        return actualBody == expectedBody
    }

    fun RecordedRequest.hasHeaders(vararg expectedHeaders: Pair<String, String>) =
        expectedHeaders.all { headers[it.first] == it.second }

    fun RecordedRequest.hasAuthorizationHeaderWith(token: String) =
        headers["Authorization"] == "Bearer $token"
}