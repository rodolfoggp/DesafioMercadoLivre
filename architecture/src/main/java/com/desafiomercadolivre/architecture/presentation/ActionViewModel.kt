package com.desafiomercadolivre.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class ActionViewModel<Action> : ViewModel() {
    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action

    fun sendAction(action: () -> Action) {
        viewModelScope.launch {
            _action.emit(action())
        }
    }
}