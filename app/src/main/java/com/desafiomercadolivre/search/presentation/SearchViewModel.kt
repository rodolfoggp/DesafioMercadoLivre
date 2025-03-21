package com.desafiomercadolivre.search.presentation

import com.desafiomercadolivre.architecture.presentation.ActionViewModel
import com.desafiomercadolivre.search.domain.IsValidQueryUseCase
import com.desafiomercadolivre.search.presentation.model.SearchAction

class SearchViewModel(
    val isValidQuery: IsValidQueryUseCase,
): ActionViewModel<SearchAction>() {
    fun onSearchButtonClicked(query: String) {
        if (isValidQuery(query)) {
            sendAction { SearchAction.Search(query.trim()) }
        }
    }
}
