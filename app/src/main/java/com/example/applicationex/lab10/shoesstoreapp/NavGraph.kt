package com.example.applicationex.lab10.shoesstoreapp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.applicationex.lab10.shoesstoreapp.controller.AuthController

import com.example.applicationex.lab10.shoesstoreapp.view.user.ForgotPasswordScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.HomeScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.LoginScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProductDetailScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProfileScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.SignupScreen
import com.example.applicationex.lab10.shoesstoreapp.view.admin.AdminDashboardScreen
import com.example.applicationex.lab10.shoesstoreapp.view.admin.ManageOrderScreen
import com.example.applicationex.lab10.shoesstoreapp.view.admin.ManageProductScreen
import com.example.applicationex.lab10.shoesstoreapp.view.admin.ReportsAndStatisticsScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.CartScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.CheckoutScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.MyOrdersScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.OrderTrackingScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ProductListScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.ResetPasswordScreen
import com.example.applicationex.lab10.shoesstoreapp.view.user.VerifyCodeScreen

@Composable
fun AppNavGraph(navController: NavHostController, authController: AuthController) {

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {   LoginScreen(
            navController = navController,
            authController = authController
        ) }
        composable("signup") { SignupScreen(navController, authController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("report_and_statistics") { ReportsAndStatisticsScreen(navController) }
        composable ("home") { HomeScreen(navController) }
        composable ("admin_home" ){AdminDashboardScreen(navController) }
        composable ("manage_product"){ ManageProductScreen(navController) }
        composable ("profile") { ProfileScreen(navController)}
        composable("discover"){ProductListScreen(navController) }
        composable("cart"){CartScreen(navController) }
        composable("my_orders") { MyOrdersScreen(navController) }
        composable("checkout"){
            CheckoutScreen(
                onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                navController= navController
            )
        }
        composable(
            "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            ProductDetailScreen(navController = navController, productId = productId.toString())
        }
        composable(
            "order_tracking/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.StringType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString("orderId") ?: return@composable
            OrderTrackingScreen(
                orderId = orderId,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("manage_orders") {
            ManageOrderScreen(navController)
        }
         }
    }