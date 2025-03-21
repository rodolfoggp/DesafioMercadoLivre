package com.desafiomercadolivre.search.presentation.model

sealed class SearchAction {
    data class Search(val query: String): SearchAction()
}