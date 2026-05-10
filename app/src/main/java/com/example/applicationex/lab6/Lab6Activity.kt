package com.example.applicationex.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applicationex.ui.theme.ApplicationExTheme
import java.text.NumberFormat
import java.util.*

class Lab6Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationExTheme {
                CupcakeApp { finish() }
            }
        }
    }
}

enum class CupcakeScreen(val title: String) {
    Start("Cupcake"),
    Flavor("Choose Flavor"),
    Pickup("Choose Pickup Date"),
    Summary("Order Summary")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeApp(onExit: () -> Unit) {
    var currentScreen by remember { mutableStateOf(CupcakeScreen.Start) }
    var quantity by remember { mutableIntStateOf(0) }
    var flavor by remember { mutableStateOf("") }
    var pickupDate by remember { mutableStateOf("") }
    val pricePerCupcake = 2.0
    val totalPrice = quantity * pricePerCupcake

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentScreen == CupcakeScreen.Start) onExit()
                        else currentScreen = getPreviousScreen(currentScreen)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFCE4EC),
                    titleContentColor = Color(0xFF880E4F)
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                CupcakeScreen.Start -> StartOrderScreen(
                    onNext = { 
                        quantity = it
                        currentScreen = CupcakeScreen.Flavor 
                    }
                )
                CupcakeScreen.Flavor -> SelectFlavorScreen(
                    onNext = {
                        flavor = it
                        currentScreen = CupcakeScreen.Pickup
                    },
                    onCancel = { currentScreen = CupcakeScreen.Start }
                )
                CupcakeScreen.Pickup -> SelectPickupScreen(
                    onNext = {
                        pickupDate = it
                        currentScreen = CupcakeScreen.Summary
                    },
                    onCancel = { currentScreen = CupcakeScreen.Start }
                )
                CupcakeScreen.Summary -> OrderSummaryScreen(
                    quantity = quantity,
                    flavor = flavor,
                    date = pickupDate,
                    totalPrice = totalPrice,
                    onSend = { onExit() },
                    onCancel = { currentScreen = CupcakeScreen.Start }
                )
            }
        }
    }
}

fun getPreviousScreen(current: CupcakeScreen): CupcakeScreen {
    return when (current) {
        CupcakeScreen.Flavor -> CupcakeScreen.Start
        CupcakeScreen.Pickup -> CupcakeScreen.Flavor
        CupcakeScreen.Summary -> CupcakeScreen.Pickup
        else -> CupcakeScreen.Start
    }
}

@Composable
fun StartOrderScreen(onNext: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🧁",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.size(160.dp).padding(bottom = 16.dp),
            color = Color(0xFFD81B60)
        )
        Text(
            text = "Order Cupcakes",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        val options = listOf(1, 6, 12)
        options.forEach { qty ->
            Button(
                onClick = { onNext(qty) },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD81B60))
            ) {
                Text(text = if (qty == 1) "One Cupcake" else "$qty Cupcakes")
            }
        }
    }
}

@Composable
fun SelectFlavorScreen(onNext: (String) -> Unit, onCancel: () -> Unit) {
    val flavors = listOf("Vanilla", "Chocolate", "Red Velvet", "Salted Caramel", "Coffee")
    var selectedFlavor by remember { mutableStateOf(flavors[0]) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        flavors.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                RadioButton(selected = (item == selectedFlavor), onClick = { selectedFlavor = item })
                Text(text = item, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
            Button(onClick = { onNext(selectedFlavor) }, modifier = Modifier.weight(1f)) { Text("Next") }
        }
    }
}

@Composable
fun SelectPickupScreen(onNext: (String) -> Unit, onCancel: () -> Unit) {
    val dates = listOf("Mon May 10", "Tue May 11", "Wed May 12", "Thu May 13")
    var selectedDate by remember { mutableStateOf(dates[0]) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        dates.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                RadioButton(selected = (item == selectedDate), onClick = { selectedDate = item })
                Text(text = item, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
            Button(onClick = { onNext(selectedDate) }, modifier = Modifier.weight(1f)) { Text("Next") }
        }
    }
}

@Composable
fun OrderSummaryScreen(quantity: Int, flavor: String, date: String, totalPrice: Double, onSend: () -> Unit, onCancel: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Order Summary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        SummaryRow("Quantity", quantity.toString())
        SummaryRow("Flavor", flavor)
        SummaryRow("Pickup Date", date)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text("Total: ", style = MaterialTheme.typography.titleMedium)
            Text(NumberFormat.getCurrencyInstance().format(totalPrice), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onSend, modifier = Modifier.fillMaxWidth().height(56.dp)) {
            Text("Send Order to Bakery")
        }
        OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth().padding(top = 12.dp)) {
            Text("Cancel")
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = label.uppercase(), style = MaterialTheme.typography.labelSmall, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
    }
}
