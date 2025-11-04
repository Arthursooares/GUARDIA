package com.example.guardia.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { 
            SplashScreen(onNavigateToLogin = { 
                // Limpa a pilha de navegação para que o usuário não volte para a splash screen
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("login") { 
            LoginScreen(
                onRegisterClick = { navController.navigate("CadastroScreen") }
            )
        }
        composable("register") { 
            CadastroScreen(onRegisterClick = { 
                // Volta para a tela de login após o cadastro
                navController.popBackStack() 
            })
        }
    }
}