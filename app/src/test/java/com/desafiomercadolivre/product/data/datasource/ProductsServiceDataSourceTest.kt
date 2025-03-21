package com.desafiomercadolivre.product.data.datasource

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.product.domain.datasource.ProductsDataSource
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.products
import com.desafiomercadolivre.sharedtests.robot.server
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ProductsServiceDataSourceTest {
    @get:Rule
    val rule = UnitTestRule()

    private val dataSource: ProductsDataSource by inject()

    private val query = "some query"
    private val accessToken = "some access token"

    @Test
    fun `datasource getByQuery should return expected products`() = runTest {
        // Given
        val expectedProducts = products { expectedProducts }
        products { searchEndpointReturnsSuccessFor(query) }

        // When
        val result = dataSource.getByQuery(query, accessToken)

        // Then
        assertThat(result).isEqualTo(expectedProducts)
    }

    @Test
    fun `datasource getByQuery should use correct token`() = runTest {
        // Given
        val expectedHeader = server { authorizationHeaderFor(accessToken) }
        products { searchEndpointReturnsSuccessFor(query) }

        // When
        dataSource.getByQuery(query, accessToken)

        // Then
        val request = server { takeRequest() }
        assertThat(request.headers).contains(expectedHeader)
    }
}