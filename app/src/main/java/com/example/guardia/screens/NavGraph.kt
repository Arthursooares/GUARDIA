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
                navController = navController,
                onItemClick = { route ->
                    when (route) {
                        // ÍCONE DE CHAT DA BOTTOM BAR
                        "chat" -> navController.navigate("guardia")

                        // Botão flutuante ou outros que mandem "home"
                        "home" -> navController.navigate("home") {
                            launchSingleTop = true
                        }

                        "perfil" -> navController.navigate("perfil")
                        "grupo"  -> navController.navigate("grupo")
                        "config" -> navController.navigate("config")

                        else -> {
                            // navController.navigate(route)
                        }
                    }
                },
                onChatClick = {
                    // Botão grande "Converse com a Guardiã"
                    navController.navigate("guardia")
                }
            )
        }

        // 🟣 Guardia (tela de chat)
        composable("guardia") {
            GuardiaScreen()
        }

        // 🔹 Tela de Dicas
        composable("tips") {
            GuardiaTipsScreen(navController = navController)
        }

        // ⭐ Upgrade / Planos
        composable("upgrade") {
            UpgradeScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // ⚙️ Configurações
        composable("config") {
            SettingsScreen(navController = navController)
        }

        // 👤 Perfil
        composable("perfil") {
            PerfilScreen(
                onItemClick = { route ->
                    when (route) {
                        "home" -> navController.navigate("home") {
                            launchSingleTop = true
                        }
                        "perfil" -> {
                        }
                        "chat" -> navController.navigate("guardia")
                        "tips" -> navController.navigate("tips")
                        "config" -> navController.navigate("config")
                    }
                },
                onNavigateToEdit = {},
                onNavigateToPlans = {
                    navController.navigate("upgrade")   // 👉 botão "Planos Guardiã" vai pra tela de planos
                }
            )
        }
    }
}
