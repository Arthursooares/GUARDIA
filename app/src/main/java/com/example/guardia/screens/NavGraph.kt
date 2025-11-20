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

                        // bottom bar:
                        "chat"   -> navController.navigate("guardia")
                        "home"   -> navController.navigate("home") { launchSingleTop = true }
                        "perfil" -> navController.navigate("perfil")
                        "tips"   -> navController.navigate("tips")
                        "config" -> navController.navigate("config")

                        else -> {}
                    }
                },
                onChatClick = {
                    navController.navigate("guardia")
                }
            )
        }

        // 🟣 Guardia (chat)
        composable("guardia") {
            GuardiaScreen()
        }

        // 🔹 Dicas
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
                        "home"   -> navController.navigate("home") { launchSingleTop = true }
                        "perfil" -> { /* já está aqui */ }
                        "chat"   -> navController.navigate("guardia")
                        "tips"   -> navController.navigate("tips")
                        "config" -> navController.navigate("config")
                    }
                },
                onNavigateToEdit = { navController.navigate("editProfile") },
                onNavigateToSecurity = { navController.navigate("security") },
                onNavigateToSaved = { navController.navigate("saved") },
                onNavigateToPlans = { navController.navigate("upgrade") }
            )
        }

        // 📝 Feedback
        composable("feedback") {
            FeedbackScreen(
                onBackClick = { navController.popBackStack() },
                onBottomItemClick = { route ->
                    when (route) {
                        "home"   -> navController.navigate("home")
                        "perfil" -> navController.navigate("perfil")
                        "chat"   -> navController.navigate("guardia")
                        "tips"   -> navController.navigate("tips")
                        "config" -> navController.navigate("config")
                    }
                }
            )
        }

        // ⭐ Editar perfil
        composable("editProfile") {
            EditScreen(onUpdateClick = { navController.popBackStack() })
        }

        // 🔐 Segurança
        composable("security") {
            SenhaScreen(onBackClick = { navController.popBackStack() })
        }

        // 💾 Salvos
        composable("saved") {
            SalvosScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
