package com.desafiomercadolivre.home.presentation

import com.desafiomercadolivre.architecture.presentation.ActionViewModel
import com.desafiomercadolivre.home.presentation.model.HomeAction

class HomeViewModel: ActionViewModel<HomeAction>() {
    fun onSearchViewClicked() = sendAction { HomeAction.ShowSearchScreen }
}