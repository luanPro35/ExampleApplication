package com.example.applicationex.lab10.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        try {
            android.util.Log.d("AppDataContainer", "Firebase repository initialized successfully")
            FirebaseItemsRepository()
        } catch (e: Exception) {
            android.util.Log.e("AppDataContainer", "Error initializing Firebase repository", e)
            throw e
        }
    }
}
