package com.example.applicationex.lab9

import android.content.Context

interface AppContainer {
    val bluromaticRepository: BluromaticRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val bluromaticRepository: BluromaticRepository by lazy {
        WorkManagerBluromaticRepository(context)
    }
}