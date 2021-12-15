package org.gsm.software.gsmranking

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@HiltAndroidApp
class MyApplication : Application() {
    private var instance : MyApplication? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}