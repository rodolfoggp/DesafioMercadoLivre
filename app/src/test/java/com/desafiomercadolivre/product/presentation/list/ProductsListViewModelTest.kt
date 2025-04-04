package com.desafiomercadolivre.product.presentation.list

import com.desafiomercadolivre.architecture.extensions.inject
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.products
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ProductsListViewModelTest {
    @get:Rule
    val rule = UnitTestRule()

    private val viewModel: ProductsListViewModel by inject()

    @Test
    fun test() = runTest {
        val query = "query"
        val expectedProducts = products { expectedProducts }
        products { searchEndpointReturnsSuccessFor(query) }

        viewModel.searchProducts(query).join()

        assertThat(viewModel.state.value.products).isEqualTo(expectedProducts)
    }
}