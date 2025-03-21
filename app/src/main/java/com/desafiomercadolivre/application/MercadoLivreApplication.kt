package com.desafiomercadolivre.application

import android.app.Application
import com.desafiomercadolivre.application.di.mercadoLivreModules
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