package com.example.guardia.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }

        composable("login") {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") },
                onLoginClick = { _, _ ->
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true } // remove tela de login da pilha
                    }
                }
            )
        }

        composable("register") {
            /* Tela de cadastro */
        }

        // 👉 nova rota adicionada
        composable("home") {
            HomeScreen()
        }
    }
}
