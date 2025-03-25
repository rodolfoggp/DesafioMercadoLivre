package com.desafiomercadolivre.architecture.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel<State>(
    initialState: State,
) : ViewModel() {
    private var _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    fun changeState(change: (State) -> State) {
        _state.value = change(_state.value)
    }
}