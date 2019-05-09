package com.akarbowy.cakelisttest

import android.app.Application
import com.akarbowy.cakelisttest.injection.AppComponent
import com.akarbowy.cakelisttest.injection.DaggerAppComponent
import com.akarbowy.cakelisttest.injection.DaggerComponentProvider
import timber.log.Timber


internal class App : Application(), DaggerComponentProvider {

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}