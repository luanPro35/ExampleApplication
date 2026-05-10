package com.example.applicationex.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationex.ui.theme.ApplicationExTheme

class Lab3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationExTheme {
                Scaffold(
                    topBar = {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TopAppBar(
                            title = { Text("Lab 3: Dice Roller") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            )
                        )
                    }
                ) { innerPadding ->
                    DiceRollerScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}

@Composable
fun DiceRollerScreen(modifier: Modifier = Modifier) {
    var rollResult by remember { mutableStateOf(1) }
    val luckyNumber = 4
    
    val message = when (rollResult) {
        luckyNumber -> "You won!"
        1 -> "So sorry! You rolled a 1. Try again!"
        2 -> "Sadly, you rolled a 2. Try again!"
        3 -> "Unfortunately, you rolled a 3. Try again!"
        5 -> "Don't cry! You rolled a 5. Try again!"
        6 -> "Apologies! You rolled a 6. Try again!"
        else -> "Roll again!"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lucky Number: $luckyNumber",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Visual Dice
        DiceFace(value = rollResult)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Message Display
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = if (rollResult == luckyNumber) Color(0xFF00B894) else Color(0xFFD63031),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = {
                rollResult = Dice(6).roll()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Text("Roll Dice", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DiceFace(value: Int) {
    Surface(
        modifier = Modifier.size(160.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color.White,
        shadowElevation = 12.dp,
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0))
    ) {
        Canvas(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            val dotRadius = 12.dp.toPx()
            val spacing = size.width / 4

            // Define possible dot positions
            val center = Offset(size.width / 2, size.height / 2)
            val topLeft = Offset(spacing, spacing)
            val topRight = Offset(size.width - spacing, spacing)
            val bottomLeft = Offset(spacing, size.height - spacing)
            val bottomRight = Offset(size.width - spacing, size.height - spacing)
            val midLeft = Offset(spacing, size.height / 2)
            val midRight = Offset(size.width - spacing, size.height / 2)

            val dots = when (value) {
                1 -> listOf(center)
                2 -> listOf(topRight, bottomLeft)
                3 -> listOf(topRight, center, bottomLeft)
                4 -> listOf(topLeft, topRight, bottomLeft, bottomRight)
                5 -> listOf(topLeft, topRight, center, bottomLeft, bottomRight)
                6 -> listOf(topLeft, topRight, bottomLeft, bottomRight, midLeft, midRight)
                else -> listOf()
            }

            dots.forEach { pos ->
                drawCircle(
                    color = Color(0xFF2D3436),
                    radius = dotRadius,
                    center = pos
                )
            }
        }
    }
}
