package com.example.applicationex.lab10.shoesstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.applicationex.lab10.shoesstoreapp.controller.AuthController


class MainActivity : ComponentActivity() {
    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize AuthController
        authController = AuthController()

        setContent {
            val navController = rememberNavController()

            AppNavGraph(
                navController = navController,
                authController = authController
            )
        }
    }
}