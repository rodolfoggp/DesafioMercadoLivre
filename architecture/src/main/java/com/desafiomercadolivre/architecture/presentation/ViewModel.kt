package com.desafiomercadolivre.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class ViewModel<State, Action>(
    initialState: State,
) : ViewModel() {
    private var _state = MutableStateFlow(initialState)
    val stateFlow: StateFlow<State> = _state

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> = _action

    fun changeState(change: (State) -> State) {
        _state.value = change(_state.value)
    }

    fun sendAction(action: () -> Action) {
        viewModelScope.launch {
            _action.emit(action())
        }
    }
}