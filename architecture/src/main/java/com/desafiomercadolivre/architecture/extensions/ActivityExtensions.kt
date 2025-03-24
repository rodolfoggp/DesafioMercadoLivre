package com.desafiomercadolivre.architecture.extensions

import android.content.Intent
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.desafiomercadolivre.architecture.presentation.ActionViewModel
import com.desafiomercadolivre.architecture.presentation.ViewModel
import kotlinx.coroutines.launch

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

fun AppCompatActivity.useEdgeToEdge() {
    enableEdgeToEdge()
    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

fun AppCompatActivity.showKeyboard() {
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun <A : AppCompatActivity> AppCompatActivity.startActivity(
    activityClass: Class<A>,
    extras: (Intent.() -> Unit)? = null
) {
    val intent = Intent(this, activityClass)
    extras?.let { intent.it() }
    startActivity(intent)
}

fun <Action> AppCompatActivity.onAction(
    viewModel: ActionViewModel<Action>,
    handleAction: (Action) -> Unit
) = lifecycleScope.launch {
    viewModel.action.collect { action -> handleAction(action) }
}

fun <State, Action> AppCompatActivity.onStateChange(
    viewModel: ViewModel<State, Action>,
    handleState: (State) -> Unit
) = lifecycleScope.launch {
    viewModel.state.collect { state -> handleState(state) }
}

fun <State, Action> AppCompatActivity.onAction(
    viewModel: ViewModel<State, Action>,
    handleAction: (Action) -> Unit
) = lifecycleScope.launch {
    viewModel.action.collect { action -> handleAction(action) }
}