package com.example.applicationex.lab10.shoesstoreapp.view.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applicationex.lab10.shoesstoreapp.controller.AdminController
import com.example.applicationex.lab10.shoesstoreapp.view.component.BottomNavigation
import kotlinx.coroutines.launch


@Composable
fun AdminDashboardScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val adminController = AdminController()
    val totalUsers = remember{mutableStateOf(0L)}
    val totalProducts = remember{mutableStateOf(0L)}
    val totalOrders = remember{mutableStateOf(0L)}
    val totalRevenue = remember{mutableStateOf(0.0)}
    LaunchedEffect(Unit){
        totalRevenue.value= adminController.getTotalRevenue()
        totalUsers.value= adminController.getTotalUsers()
        totalProducts.value= adminController.getTotalProducts()
        totalOrders.value= adminController.getTotalOrders()

    }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
                currentRoute = navController.currentDestination?.route
            )
        }
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Admin Dashboard",
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
                )

                // Revenue and Orders Stats
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                    StatCard(
                        title = "Total Revenue",
                        value = "$${totalRevenue.value.toDouble()}",
                        icon = Icons.Default.Info,
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF4CAF50)
                    )
                    StatCard(
                        title = "Orders",
                        value ="${totalOrders.value}",
                        icon = Icons.Default.ShoppingCart,
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF2196F3)
                    )
                }

                // Products and Users Stats
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatCard(
                        title = "Total product",
                        value = "${totalProducts.value}",
                        icon = Icons.Default.List,
                        modifier = Modifier.weight(1f),
                        color = Color(0xFFFFA000)
                    )
                    StatCard(
                        title = "Clients",
                        value = "${totalUsers.value}",
                        icon = Icons.Filled.Person, // Changed from Outlined.People
                        modifier = Modifier.weight(1f),
                        color = Color(0xFF9C27B0)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Performance Metrics
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Thống Kê Hiệu Suất",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            PerformanceMetric(title = "Sản phẩm bán chạy", value = "Giày Nike Air")
                            PerformanceMetric(title = "Doanh thu hôm nay", value = "₫2,500,000")
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            PerformanceMetric(title = "Đơn hàng hôm nay", value = "12")
                            PerformanceMetric(title = "Tỷ lệ hoàn thành", value = "95%")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Action Buttons
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Quản Lý",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Button(
                        onClick = { navController.navigate("manage_product") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Manage products")
                        }
                    }

                    Button(
                        onClick = { navController.navigate("manage_orders") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Manage orders")
                        }
                    }

                    Button(
                        onClick = {
                            navController.navigate("report_and_statistics")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Reports & Statistics")
                        }
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                                val sampleProducts = listOf(
                                    hashMapOf(
                                        "name" to "Nike Air Max 270",
                                        "brand" to "Nike",
                                        "price" to 150.0,
                                        "sizes" to listOf("40", "41", "42", "43"),
                                        "quantity" to 10,
                                        "image" to "https://images.pexels.com/photos/2529148/pexels-photo-2529148.jpeg",
                                        "description" to "Comfortable and stylish Nike Air Max 270.",
                                        "rating" to 4.5,
                                        "reviews" to emptyList<Map<String, Any>>()
                                    ),
                                    hashMapOf(
                                        "name" to "Adidas Ultraboost",
                                        "brand" to "Adidas",
                                        "price" to 180.0,
                                        "sizes" to listOf("39", "40", "41", "42"),
                                        "quantity" to 5,
                                        "image" to "https://images.pexels.com/photos/1598505/pexels-photo-1598505.jpeg",
                                        "description" to "High-performance running shoes from Adidas.",
                                        "rating" to 4.8,
                                        "reviews" to emptyList<Map<String, Any>>()
                                    ),
                                    hashMapOf(
                                        "name" to "Jordan 1 Retro",
                                        "brand" to "Jordan",
                                        "price" to 220.0,
                                        "sizes" to listOf("41", "42", "43", "44"),
                                        "quantity" to 3,
                                        "image" to "https://images.pexels.com/photos/1598508/pexels-photo-1598508.jpeg",
                                        "description" to "Classic Jordan 1 Retro high-top sneakers.",
                                        "rating" to 4.9,
                                        "reviews" to emptyList<Map<String, Any>>()
                                    ),
                                    hashMapOf(
                                        "name" to "Puma Cali",
                                        "brand" to "Puma",
                                        "price" to 90.0,
                                        "sizes" to listOf("37", "38", "39", "40"),
                                        "quantity" to 15,
                                        "image" to "https://images.pexels.com/photos/1598507/pexels-photo-1598507.jpeg",
                                        "description" to "Trendy Puma Cali sneakers for everyday wear.",
                                        "rating" to 4.2,
                                        "reviews" to emptyList<Map<String, Any>>()
                                    )
                                )
                                
                                sampleProducts.forEach { product ->
                                    db.collection("products").add(product)
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text("Seed Sample Data")
                        }
                    }
                }
            }
        }

    }

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.height(120.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row (modifier=Modifier,
                verticalAlignment = Alignment.CenterVertically,){
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Row {
                Text(
                    text = value,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Composable
fun PerformanceMetric(title: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
