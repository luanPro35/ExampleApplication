package com.example.applicationex.lab7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationex.ui.theme.ApplicationExTheme
import com.google.firebase.firestore.FirebaseFirestore

class Lab7Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationExTheme {
                FirebaseAppWrapper { finish() }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseAppWrapper(onExit: () -> Unit) {
    var currentScreen by remember { mutableStateOf("add") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("GFG", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { 
                        if (currentScreen == "view") currentScreen = "add" else onExit() 
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF1B9E5A) // Matching the green in image
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            color = Color.White
        ) {
            if (currentScreen == "add") {
                AddCourseScreen(onViewCourses = { currentScreen = "view" })
            } else {
                ViewCoursesScreen()
            }
        }
    }
}

@Composable
fun AddCourseScreen(onViewCourses: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Custom TextFields matching the image style
        CourseTextField(value = name, onValueChange = { name = it }, label = "Course Name")
        Spacer(modifier = Modifier.height(24.dp))
        CourseTextField(value = duration, onValueChange = { duration = it }, label = "Course Duration")
        Spacer(modifier = Modifier.height(24.dp))
        CourseTextField(value = description, onValueChange = { description = it }, label = "Course Description")
        
        Spacer(modifier = Modifier.height(48.dp))

        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFF6750A4))
        } else {
            Button(
                onClick = {
                    if (name.isBlank() || duration.isBlank() || description.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        isLoading = true
                        val db = FirebaseFirestore.getInstance()
                        val course = hashMapOf(
                            "courseName" to name,
                            "courseDuration" to duration,
                            "courseDescription" to description
                        )
                        db.collection("Courses").add(course)
                            .addOnSuccessListener {
                                isLoading = false
                                Toast.makeText(context, "Course Added Successfully", Toast.LENGTH_SHORT).show()
                                name = ""; duration = ""; description = ""
                            }
                            .addOnFailureListener {
                                isLoading = false
                                Toast.makeText(context, "Failed to add course: ${it.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text("Add Data", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onViewCourses,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text("View Courses", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CourseTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFEADDFF),
            unfocusedContainerColor = Color(0xFFEADDFF),
            disabledContainerColor = Color(0xFFEADDFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun ViewCoursesScreen() {
    val db = FirebaseFirestore.getInstance()
    var courseList by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        db.collection("Courses").get()
            .addOnSuccessListener { result ->
                courseList = result.map { it.data }
                isLoading = false
            }
            .addOnFailureListener {
                isLoading = false
            }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(courseList) { course ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = course["courseName"].toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1B9E5A)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = course["courseDuration"].toString(), style = MaterialTheme.typography.bodyMedium)
                        Text(text = course["courseDescription"].toString(), style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
