package com.example.applicationex.lab7

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationExTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddCourseScreen()
                }
            }
        }
    }
}

@Composable
fun AddCourseScreen() {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()
    
    var courseName by remember { mutableStateOf("") }
    var courseDuration by remember { mutableStateOf("") }
    var courseDescription by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Course",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = courseName,
            onValueChange = { courseName = it },
            label = { Text("Course Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = courseDuration,
            onValueChange = { courseDuration = it },
            label = { Text("Course Duration") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = courseDescription,
            onValueChange = { courseDescription = it },
            label = { Text("Course Description") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = {
                if (courseName.isNotEmpty() && courseDuration.isNotEmpty() && courseDescription.isNotEmpty()) {
                    isLoading = true
                    
                    val course = Course(
                        name = courseName,
                        duration = courseDuration,
                        description = courseDescription
                    )
                    
                    db.collection("courses")
                        .add(course)
                        .addOnSuccessListener {
                            isLoading = false
                            Toast.makeText(context, "Course added successfully!", Toast.LENGTH_SHORT).show()
                            // Clear form
                            courseName = ""
                            courseDuration = ""
                            courseDescription = ""
                        }
                        .addOnFailureListener { e ->
                            isLoading = false
                            Toast.makeText(context, "Error adding course: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Add Course")
            }
        }
    }
}