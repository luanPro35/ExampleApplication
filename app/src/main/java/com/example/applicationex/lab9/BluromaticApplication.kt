package com.example.applicationex.lab9

import android.app.Application
import android.util.Log
import androidx.work.WorkManager
import androidx.work.Configuration
import com.example.applicationex.lab9.AppContainer
import com.example.applicationex.lab9.DefaultAppContainer

class BluromaticApplication : Application(), Configuration.Provider {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        Log.d("BluromaticApp", "Application onCreate started")
        container = DefaultAppContainer(this)
        Log.d("BluromaticApp", "Application onCreate completed")
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()
}