package com.desafiomercadolivre.product.data.repository

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.product.domain.repository.ProductsRepository
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.accessToken
import com.desafiomercadolivre.sharedtests.robot.products
import com.desafiomercadolivre.sharedtests.robot.server
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ProductsRepositoryImplTest {
    @get:Rule
    val rule = UnitTestRule()

    private val repository: ProductsRepository by inject()

    private val query = "some query"

    @Test
    fun `getByQuery should return products from service`() = runTest {
        // Given
        products { searchEndpointReturnsSuccessFor(query) }
        val expectedProducts = products { expectedProducts }

        // When
        val result = repository.getByQuery(query)

        // Then
        assertThat(result).isEqualTo(expectedProducts)
    }

    @Test
    fun `getByQuery should use correct access token`() = runTest {
        // Given
        val accessToken = accessToken { get() }
        products { searchEndpointReturnsSuccessFor(query) }
        val expectedHeader = server { authorizationHeaderFor(accessToken) }

        // When
        repository.getByQuery(query)

        // Then
        val request = server { takeRequest() }
        assertThat(request.headers).contains(expectedHeader)
    }
}