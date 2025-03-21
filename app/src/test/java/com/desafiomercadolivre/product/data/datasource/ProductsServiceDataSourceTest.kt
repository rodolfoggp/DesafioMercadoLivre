package com.desafiomercadolivre.product.data.datasource

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.product.domain.datasource.ProductsDataSource
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.productsService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ProductsServiceDataSourceTest {
    @get:Rule
    val rule = UnitTestRule()

    private val dataSource: ProductsDataSource by inject()

    @Test
    fun `datasource should return expected products when getByQuery is called`() = runTest {
        // Given
        val query = "some query"
        val products = productsService {
            returnsSuccessFor(query)
            expectedProducts
        }

        // When
        val result = dataSource.getByQuery(query, "some token")

        // Then
        assertThat(result).isEqualTo(products)
    }
}