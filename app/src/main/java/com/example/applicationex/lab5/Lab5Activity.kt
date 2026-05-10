package com.example.applicationex.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationex.ui.theme.ApplicationExTheme

class Lab5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationExTheme {
                Scaffold(
                    topBar = { WoofTopAppBar { finish() } },
                    containerColor = Color(0xFFF8F9FA)
                ) { innerPadding ->
                    WoofList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

data class Dog(
    val name: String,
    val age: Int,
    val hobbies: String,
    val color: Color
)

val dogs = listOf(
    Dog("Koda", 2, "Eating treats and chasing squirrels", Color(0xFFE17055)),
    Dog("Lola", 16, "Barking at the mailman", Color(0xFF00B894)),
    Dog("Frankie", 2, "Digging holes in the garden", Color(0xFF0984E3)),
    Dog("Nox", 8, "Sleeping in the sun", Color(0xFF6C5CE7)),
    Dog("Faye", 8, "Playing fetch for hours", Color(0xFFD63031)),
    Dog("Bella", 14, "Going for long walks", Color(0xFFFDCB6E)),
    Dog("Moana", 2, "Swimming in the ocean", Color(0xFFE84393)),
    Dog("Tzeitel", 7, "Cuddling on the couch", Color(0xFF2D3436))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar(onBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "🐕",
                    modifier = Modifier.size(32.dp).padding(end = 8.dp),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = "Woof",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 28.sp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun WoofList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(dogs) { dog ->
            DogItem(dog = dog)
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
fun DogItem(dog: Dog) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (expanded) MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f) 
                            else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (expanded) 6.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Dog Avatar Placeholder
                Surface(
                    modifier = Modifier.size(64.dp).clip(CircleShape),
                    color = dog.color.copy(alpha = 0.2f)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "🐕",
                            style = MaterialTheme.typography.displaySmall,
                            color = dog.color,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                
                Column(
                    modifier = Modifier.padding(start = 16.dp).weight(1f)
                ) {
                    Text(
                        text = dog.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${dog.age} years old",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand/Collapse"
                    )
                }
            }
            
            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp)
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = "ABOUT",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = dog.hobbies,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}
