package com.example.applicationex.lab10

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.applicationex.ui.theme.ApplicationExTheme
import com.example.applicationex.lab10.ui.theme.InventoryTheme

class Lab10Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lab10Activity", "onCreate started")
        
        try {
            setContent {
                InventoryTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        InventoryApp()
                    }
                }
            }
            Log.d("Lab10Activity", "InventoryApp loaded successfully")
        } catch (e: Exception) {
            Log.e("Lab10Activity", "Error in Lab10Activity", e)
            try {
                setContent {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.error
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Lab10 Error: ${e.message}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            } catch (e2: Exception) {
                Log.e("Lab10Activity", "Even error screen failed", e2)
            }
        }
    }
}

@Composable
fun TestScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "Lab10 Test Screen",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Testing basic components...",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
