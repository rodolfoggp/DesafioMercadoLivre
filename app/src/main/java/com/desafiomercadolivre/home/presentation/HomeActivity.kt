package com.desafiomercadolivre.home.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        searchBar.setOnClickListener {
            startActivity(Intent(this@HomeActivity, SearchActivity::class.java))
        }
    }
}