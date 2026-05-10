package com.example.applicationex.lab10.shoesstoreapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationex.lab10.shoesstoreapp.view.user.LoginScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.HomeScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProductListScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProductDetailScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.CartScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.CheckoutScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProfileScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.MyOrdersScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.OrderTrackingScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ForgotPasswordScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.SignupScreen
import com.example.applicationex.lab10.shoesstoreapp.controller.AuthController

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController, authController = AuthController())
        }
        
        composable("signup") {
            SignupScreen(navController = navController, authController = AuthController())
        }
        
        composable("forgot_password") {
            ForgotPasswordScreen(navController = navController)
        }
        
        composable("home") {
            HomeScreen(navController = navController)
        }
        
        composable("discover") {
            ProductListScreen(navController = navController)
        }
        
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductDetailScreen(
                productId = productId,
                navController = navController
            )
        }
        
        composable("cart") {
            CartScreen(navController = navController)
        }
        
        composable("checkout") {
            CheckoutScreen(
                onBackClick = { navController.navigateUp() },
                navController = navController
            )
        }
        
        composable("profile") {
            ProfileScreen(navController = navController)
        }
        
        composable("my_orders") {
            MyOrdersScreen(navController = navController)
        }
        
        composable("order_tracking/{orderId}") { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: ""
            OrderTrackingScreen(
                orderId = orderId,
                onBackClick = { navController.navigateUp() },
                orderController = remember { com.example.applicationex.lab10.shoesstoreapp.controller.OrderController() }
            )
        }
    }
}
