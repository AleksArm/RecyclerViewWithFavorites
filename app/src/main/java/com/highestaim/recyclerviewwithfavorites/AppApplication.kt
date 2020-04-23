package com.highestaim.recyclerviewwithfavorites

import android.app.Application
import com.highestaim.recyclerviewwithfavorites.DI.Services
import com.highestaim.recyclerviewwithfavorites.DI.appRepositories
import com.highestaim.recyclerviewwithfavorites.DI.appViewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appRepositories, appViewModels, Services))
        }
    }
}