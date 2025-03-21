package com.desafiomercadolivre.common.presentation

import android.app.Application
import com.desafiomercadolivre.common.di.mercadoLivreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MercadoLivreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MercadoLivreApplication)
            modules(mercadoLivreModules)
        }
    }
}