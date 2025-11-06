package com.example.guardia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guardia.ui.theme.GuardiaTheme

@Composable
fun HomeScreen(
    userName: String = "Lívia",
    onMenuClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE8F5FF),
                        Color(0xFFD3ECFF),
                        Color(0xFFE8F5FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 🔹 TOP BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color(0xFF0E3B5E)
                    )
                }
            }

            // 🔹 CONTEÚDO SCROLLÁVEL
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Bem vindo(a), $userName!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0E3B5E),
                    modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                )

                // 🔹 CARD PRINCIPAL
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF33B2B2),
                                    Color(0xFF0E6D90)
                                )
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Converse com a Guardiã",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Alguém da comunidade pode te ajudar. Vamos resolver isso juntos!",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                onClick = onChatClick,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE8F5FF)
                                ),
                                shape = RoundedCornerShape(50),
                                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Comece agora",
                                    color = Color(0xFF0E6D90),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // placeholder para personagem
                        Box(
                            modifier = Modifier
                                .width(90.dp)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(70.dp)
                                    .height(120.dp)
                                    .background(Color.Transparent)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // 🔹 LISTA DE CARDS (tipada explicitamente)
                val cards: List<HomeCardData> = remember {
                    listOf(
                        HomeCardData("Dicas de segurança", Icons.Filled.Security),
                        HomeCardData("Meus Relatórios", Icons.Filled.Description),
                        HomeCardData("Upgrade Guardiã", Icons.Filled.Star),
                        HomeCardData("Feedbacks", Icons.Filled.ChatBubble)
                    )
                }

                cards.forEach { item ->
                    HomeListItem(
                        title = item.title,
                        icon = item.icon,
                        onClick = { onItemClick(item.title) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(80.dp))
            }

            // 🔹 BOTTOM NAV
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Início",
                            tint = Color(0xFF0E3B5E)
                        )
                    },
                    label = { Text("Início", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Description,
                            contentDescription = "Relatórios",
                            tint = Color(0xFF9AA9B5)
                        )
                    },
                    label = { Text("Relatórios", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Guardiã",
                            tint = Color(0xFF9AA9B5)
                        )
                    },
                    label = { Text("Guardiã", fontSize = 10.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Perfil",
                            tint = Color(0xFF9AA9B5)
                        )
                    },
                    label = { Text("Perfil", fontSize = 10.sp) }
                )
            }
        }
    }
}


@Composable
private fun HomeListItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFD3ECFF), shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF0E3B5E)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF0E3B5E)
        )
    }
}



data class HomeCardData(
    val title: String,
    val icon: ImageVector
)


@Preview(showBackground = true, backgroundColor = 0xFFE8F5FF)
@Composable
fun HomeScreenPreview() {
    GuardiaTheme {
        HomeScreen()
    }
}