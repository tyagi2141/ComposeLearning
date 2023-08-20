package com.example.composelearning

import android.app.Application
import com.example.composelearning.di.appModule
import com.example.composelearning.di.getShareModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getShareModule())
        }
    }
}