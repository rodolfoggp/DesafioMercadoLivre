package com.desafiomercadolivre.architecture.extensions

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding

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

fun <A : AppCompatActivity> AppCompatActivity.startActivity(
    activityClass: Class<A>,
    extras: (Intent.() -> Unit)? = null
) {
    val intent = Intent(this, activityClass)
    extras?.let { intent.it() }
    startActivity(intent)
}