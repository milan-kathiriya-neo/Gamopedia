package org.example.gameopedia

import android.app.Application
import org.example.gameopedia.di.initKoin

class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}