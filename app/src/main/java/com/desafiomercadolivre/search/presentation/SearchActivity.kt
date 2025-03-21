package com.desafiomercadolivre.search.presentation

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.onTextChanged
import com.desafiomercadolivre.architecture.extensions.showKeyboard
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivitySearchBinding
import com.desafiomercadolivre.product.presentation.ProductsListActivity
import com.desafiomercadolivre.search.presentation.model.SearchAction
import com.desafiomercadolivre.search.presentation.model.SearchAction.Search
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySearchBinding::inflate)
    private val viewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupLayout()
        onAction(viewModel, ::handleAction)
    }

    override fun onResume() {
        super.onResume()
        focusSearchEditText()
    }

    private fun setupLayout() {
        setupBackButton()
        setupEditText()
        setupClearButton()
    }

    private fun setupBackButton() = with(binding) {
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupEditText() = with(binding) {
        editText.onTextChanged { text ->
            clearButton.isVisible = !text.isNullOrBlank()
        }
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = editText.text.toString()
                viewModel.onSearchButtonClicked(query)
                true
            } else {
                false
            }
        }
    }

    private fun setupClearButton() = with(binding) {
        clearButton.setOnClickListener {
            editText.setText("")
        }
    }

    private fun focusSearchEditText() = with(binding) {
        editText.requestFocus()
        showKeyboard()
    }

    private fun handleAction(action: SearchAction) = when (action) {
        is Search -> goToProductsListActivity(action.query)
    }


    private fun goToProductsListActivity(query: String) {
        startActivity(ProductsListActivity::class.java) {
            putExtra(ProductsListActivity.QUERY, query)
        }
    }
}
