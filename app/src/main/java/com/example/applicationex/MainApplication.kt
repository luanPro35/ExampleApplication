package com.example.applicationex

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.example.applicationex.lab9.AppContainer as Lab9Container
import com.example.applicationex.lab9.DefaultAppContainer as Lab9DataContainer
import com.example.applicationex.lab10.data.AppContainer as Lab10Container
import com.example.applicationex.lab10.data.AppDataContainer as Lab10DataContainer
import com.google.firebase.FirebaseApp

class MainApplication : Application(), Configuration.Provider {

    lateinit var lab9Container: Lab9Container
    lateinit var lab10Container: Lab10Container

    override fun onCreate() {
        super.onCreate()
        Log.d("MainApplication", "Application onCreate started")
        
        // Initialize Firebase
        if (FirebaseApp.getInstance() == null) {
            FirebaseApp.initializeApp(this)
            Log.d("MainApplication", "Firebase initialized successfully")
        }
        
        try {
            lab9Container = Lab9DataContainer(this)
            Log.d("MainApplication", "Lab9 container initialized successfully")
        } catch (e: Exception) {
            Log.e("MainApplication", "Error initializing Lab9 container", e)
        }
        
        try {
            lab10Container = Lab10DataContainer(this)
            Log.d("MainApplication", "Lab10 container initialized successfully")
        } catch (e: Exception) {
            Log.e("MainApplication", "Error initializing Lab10 container", e)
        }
        
        Log.d("MainApplication", "Application onCreate completed")
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()
}
