package com.desafiomercadolivre.search.di

import com.desafiomercadolivre.search.domain.IsValidQueryUseCase
import com.desafiomercadolivre.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel { SearchViewModel(isValidQuery = get()) }
    factory { IsValidQueryUseCase() }
}