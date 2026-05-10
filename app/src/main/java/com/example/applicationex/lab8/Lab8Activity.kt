package com.example.applicationex.lab8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationex.ui.theme.ApplicationExTheme
import com.google.firebase.auth.FirebaseAuth

class Lab8Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationExTheme {
                AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp() {
    val auth = FirebaseAuth.getInstance()
    var currentScreen by remember { mutableStateOf(if (auth.currentUser != null) "home" else "login") }
    
    Surface(modifier = Modifier.fillMaxSize()) {
        when (currentScreen) {
            "login" -> LoginScreen(
                onNavigateToRegister = { currentScreen = "register" },
                onLoginSuccess = { currentScreen = "home" }
            )
            "register" -> RegisterScreen(
                onNavigateToLogin = { currentScreen = "login" },
                onRegisterSuccess = { currentScreen = "home" }
            )
            "home" -> IceCreamShopHome(
                onLogout = { 
                    auth.signOut()
                    currentScreen = "login" 
                }
            )
        }
    }
}

@Composable
fun LoginScreen(onNavigateToRegister: () -> Unit, onLoginSuccess: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sweet Treats", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFFE91E63))
        Text("Login to your account", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(if (passwordVisible) "👁" else "👁‍🗨")
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            shape = RoundedCornerShape(12.dp)
        )

        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFFE91E63))
        } else {
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                    } else {
                        isLoading = true
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener {
                                isLoading = false
                                onLoginSuccess()
                            }
                            .addOnFailureListener {
                                isLoading = false
                                Toast.makeText(context, "Login Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        TextButton(onClick = onNavigateToRegister, modifier = Modifier.padding(top = 16.dp)) {
            Text("Don't have an account? Register here", color = Color(0xFFE91E63))
        }
    }
}

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit, onRegisterSuccess: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Join the Fun", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF2196F3))
        Text("Create an account to start shopping", fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            shape = RoundedCornerShape(12.dp)
        )

        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFF2196F3))
        } else {
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    } else {
                        isLoading = true
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnSuccessListener {
                                isLoading = false
                                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                                onRegisterSuccess()
                            }
                            .addOnFailureListener {
                                isLoading = false
                                Toast.makeText(context, "Registration Failed: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Register", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        TextButton(onClick = onNavigateToLogin, modifier = Modifier.padding(top = 16.dp)) {
            Text("Already have an account? Login here", color = Color(0xFF2196F3))
        }
    }
}

data class IceCream(val id: Int, val name: String, val price: Double, val emoji: String, val color: Color)

val iceCreams = listOf(
    IceCream(1, "Vanilla Dream", 2.50, "🍦", Color(0xFFFFF9C4)),
    IceCream(2, "Choco Blast", 3.00, "🍨", Color(0xFFD7CCC8)),
    IceCream(3, "Berry Swirl", 3.50, "🍧", Color(0xFFF8BBD0)),
    IceCream(4, "Matcha Green", 4.00, "🍵", Color(0xFFDCEDC8)),
    IceCream(5, "Rainbow Cup", 4.50, "🧁", Color(0xFFFFE0B2)),
    IceCream(6, "Cool Mint", 3.00, "❄️", Color(0xFFB2EBF2))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IceCreamShopHome(onLogout: () -> Unit) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Ice Cream Shop", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onLogout) {
                        Text("↩", color = Color.Red)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFCE4EC)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFFFBFD))
        ) {
            // Header Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Hello, ${user?.email?.split("@")?.get(0) ?: "Foodie"}!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.DarkGray
                )
                Text(
                    text = "What sweet treat are you looking for?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            // Products Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(iceCreams) { item ->
                    IceCreamCard(item)
                }
            }
        }
    }
}

@Composable
fun IceCreamCard(item: IceCream) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* Detail */ },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(item.color),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item.emoji, fontSize = 40.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF424242)
            )
            Text(
                text = "$${String.format("%.2f", item.price)}",
                color = Color(0xFFE91E63),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /* Add to cart */ },
                modifier = Modifier.fillMaxWidth().height(36.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFCE4EC), contentColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Add", fontSize = 12.sp)
            }
        }
    }
}
