package com.desafiomercadolivre.home.presentation.model

sealed class HomeAction {
    data object ShowSearchScreen: HomeAction()
}