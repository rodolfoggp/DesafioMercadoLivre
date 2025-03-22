package com.desafiomercadolivre.product.data.service

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.common.data.model.BearerAuthorization
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.products
import com.desafiomercadolivre.sharedtests.robot.server
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsServiceTest {
    @get:Rule
    val rule = UnitTestRule()

    private val service: ProductsService by inject()

    private val query = "query"
    private val accessToken = "token"
    private val header = BearerAuthorization(accessToken)

    @Before
    fun setUp() {
        products { searchEndpointReturnsSuccessFor(query) }
    }

    @Test
    fun `search should send a GET request`() = runTest {
        // When
        service.search(query, header)

        // Then
        val method = server { takeRequest().method }
        assertThat(method).isEqualTo("GET")
    }

    @Test
    fun `search path should contain query in variable q`() = runTest {
        // When
        service.search(query, header)

        // Then
        val path = server { takeRequest().path }
        assertThat(path).isEqualTo("/sites/MLB/search?q=query")
    }

    @Test
    fun `search should include access token in authorization header`() = runTest {
        // Given
        val expectedHeader = server { authorizationHeaderFor(accessToken) }

        // When
        service.search(query, header)

        // Then
        val headers = server { takeRequest().headers }
        assertThat(headers).contains(expectedHeader)
    }
}