package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Cores usadas na bottom bar
private val TitleDark  = Color(0xFF0E3B5E)
private val PrimaryTeal = Color(0xFF33B2B2)
private val PrimaryBlue = Color(0xFF0E6D90)

/**
 * Bottom navigation reutilizável.
 *
 * @param currentRoute rota atual (ex: "home", "perfil", "chat"...)
 * @param onItemClick callback quando um item é clicado (recebe a rota)
 */
@Composable
fun GuardiaBottomBar(
    currentRoute: String,
    onItemClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()   // sobe a barra acima dos botões/gestos do sistema
            .padding(bottom = 4.dp)
            .height(80.dp)            // altura total (barra + FAB central)
    ) {
        // Fundo da barra
        Surface(
            color = Color.White,
            shadowElevation = 12.dp,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(64.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Perfil
                IconButton(
                    onClick = { onItemClick("perfil") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = if (currentRoute == "perfil") PrimaryBlue else TitleDark
                    )
                }

                // Chat
                IconButton(
                    onClick = { onItemClick("chat") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChatBubble,
                        contentDescription = "Chat",
                        tint = if (currentRoute == "chat") PrimaryBlue else TitleDark
                    )
                }

                // Espaço pro botão central flutuante
                Spacer(modifier = Modifier.width(56.dp))

                // Dicas (ANTES era Grupo)
                IconButton(
                    onClick = { onItemClick("tips") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb,
                        contentDescription = "Dicas",
                        tint = if (currentRoute == "tips") PrimaryBlue else TitleDark
                    )
                }

                // Configurações
                IconButton(
                    onClick = { onItemClick("config") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Configurações",
                        tint = if (currentRoute == "config") PrimaryBlue else TitleDark
                    )
                }
            }
        }

        // Botão central flutuante (Home)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
                .size(64.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                    clip = false
                )
                .clip(CircleShape)
                .background(
                    Brush.verticalGradient(
                        listOf(PrimaryTeal, PrimaryBlue)
                    )
                )
                .clickable { onItemClick("home") },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Início",
                tint = Color.White
            )
        }
    }
}
