package com.desafiomercadolivre.search.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.desafiomercadolivre.architecture.extensions.onTextChanged
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySearchBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupLayout()
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
    }

    private fun setupClearButton() = with(binding) {
        clearButton.setOnClickListener {
            editText.setText("")
        }
    }
}

