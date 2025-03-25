package com.desafiomercadolivre.home.presentation

import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.desafiomercadolivre.architecture.extensions.startActivity
import com.desafiomercadolivre.architecture.extensions.useEdgeToEdge
import com.desafiomercadolivre.architecture.extensions.viewBinding
import com.desafiomercadolivre.databinding.ActivityHomeBinding
import com.desafiomercadolivre.search.presentation.SearchActivity

class HomeActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        useEdgeToEdge()
        setupSearchBarAction()
    }

    private fun setupSearchBarAction() = with(binding) {
        toolbar.searchView.setOnClickListener {
            startActivity(SearchActivity::class.java) {
                addFlags(FLAG_ACTIVITY_NO_HISTORY)
            }
        }
    }
}