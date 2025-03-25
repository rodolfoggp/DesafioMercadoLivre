package com.desafiomercadolivre.home.presentation

import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.desafiomercadolivre.architecture.extensions.onAction
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityHomeBinding
import com.desafiomercadolivre.home.presentation.model.HomeAction
import com.desafiomercadolivre.home.presentation.model.HomeAction.ShowSearchScreen
import com.desafiomercadolivre.search.presentation.SearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupSearchBarAction()
        onAction(viewModel, ::handleAction)
    }

    private fun setupSearchBarAction() = with(binding) {
        toolbar.searchView.setOnClickListener { viewModel.onSearchViewClicked() }
    }

    private fun handleAction(action: HomeAction) {
        when (action) {
            ShowSearchScreen -> showSearchScreen()
        }
    }

    private fun showSearchScreen() {
        startActivity(SearchActivity::class.java) {
            addFlags(FLAG_ACTIVITY_NO_HISTORY)
        }
    }
}