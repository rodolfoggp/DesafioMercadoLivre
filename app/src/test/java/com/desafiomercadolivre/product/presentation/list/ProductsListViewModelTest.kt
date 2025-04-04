package com.desafiomercadolivre.product.presentation.list

import com.desafiomercadolivre.architecture.extensions.getFromKoin
import com.desafiomercadolivre.rule.UnitTestRule
import com.desafiomercadolivre.sharedtests.robot.products
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProductsListViewModelTest {
    @get:Rule
    val rule = UnitTestRule()

    private lateinit var viewModel: ProductsListViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        viewModel = ProductsListViewModel(getFromKoin(), dispatcher)
    }

    @Test
    fun test() = runTest(dispatcher) {
        val query = "query"
        val expectedProducts = products { expectedProducts }
        products { searchEndpointReturnsSuccessFor(query) }

        viewModel.searchProducts(query).join()

        assertThat(viewModel.state.value.products).isEqualTo(expectedProducts)
    }
}