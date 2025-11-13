package com.example.guardia.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        // 🟢 Splash
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate("login")
                }
            )
        }


        // 🟡 Login
        composable("login") {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate("register")
                },
                onLoginClick = { _, _ ->
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // 🔵 Home
        composable("home") {
            HomeScreen(
                navController = navController, // ✅ passa o NavController aqui!
                onItemClick = { route ->
                    navController.navigate(route)
                },
                onChatClick = {
                    navController.navigate("guardia")
                }
            )
        }

        // 🟣 Guardia
        composable("guardia") {
            GuardiaScreen()
        }

        composable("tips") {
            GuardiaTipsScreen(onBackClick = { navController.popBackStack() })
        }


    }

}
